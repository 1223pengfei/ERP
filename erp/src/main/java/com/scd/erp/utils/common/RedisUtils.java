package com.scd.erp.utils.common;

import com.scd.erp.utils.extraUtil.ConsoleMsg;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {

	private static final String LOCK_PREFIX = "lock_";
	protected long LOCK_EXPIRE = 1000;

	@Resource
	private RedisTemplate redisTemplate;

//	@Autowired
//	public redisUtils(RedisTemplate redisTemplate) {
//		this.redisTemplate = redisTemplate;
//	}

	/**
     * 最终加强分布式锁
     * @param key key值
     * @return 是否获取到
     */
public boolean lock(String key) {
        String lock = LOCK_PREFIX + key;
        // 利用lambda表达式
        return (Boolean) redisTemplate.execute(new RedisCallback<Object>() {
        	long expireAt = System.currentTimeMillis() + LOCK_EXPIRE + 1;
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                Boolean acquire = redisConnection.setNX(lock.getBytes(), String.valueOf(expireAt).getBytes());
                if (acquire) {
                    return true;
                } else {
                    byte[] value = redisConnection.get(lock.getBytes());
                    if (Objects.nonNull(value) && value.length > 0) {
                        long expireTime = Long.parseLong(new String(value));
                        if (expireTime < System.currentTimeMillis()) {
                            // 如果锁已经过期
                            byte[] oldValue = redisConnection.getSet(lock.getBytes(), String.valueOf(System.currentTimeMillis() + LOCK_EXPIRE + 1).getBytes());
                            // 防止死锁
                            return Long.parseLong(new String(oldValue)) < System.currentTimeMillis();
                        }
                    }
                }
                return false;
            }
        });
    }

		/**
		 * 删除锁
		 *
		 * @param key
		 */
		public void delete(String key) {
		    redisTemplate.delete(LOCK_PREFIX+key);
		}
		/**
		 * 删除锁
		 *
		 * @param keys
		 */
		public void delete(Set keys) {
			redisTemplate.delete(keys);
		}

		/**
	     * 指定缓存失效时间
	     * @param key 键
	     * @param time 时间(秒)
	     * @return
	     */
	    public boolean expire(String key,long time){
	        try {
	            if(time>0){
	                redisTemplate.expire(key, time, TimeUnit.SECONDS);
	            }
	            return true;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

	    /**
	     * 根据key 获取过期时间
	     * @param key 键 不能为null
	     * @return 时间(秒) 返回0代表为永久有效
	     */
	    public long getExpire(String key){
	        return redisTemplate.getExpire(key,TimeUnit.SECONDS);
	    }

	    /**
	     * 判断key是否存在
	     * @param key 键
	     * @return true 存在 false不存在
	     */
	    public boolean hasKey(String key){
	        try {
	            return redisTemplate.hasKey(key);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

	    /**
	     * 删除缓存
	     * @param key 可以传一个值 或多个
	     */
	    @SuppressWarnings("unchecked")
	    public void del(String ... key){
	        if(key!=null&&key.length>0){
	            if(key.length==1){
	                redisTemplate.delete(key[0]);
	            }else{
	                redisTemplate.delete(CollectionUtils.arrayToList(key));
	            }
	        }
	    }

	    /**
	     * 获取缓存
	     * @param key 键
	     * @return 值
	     */
	    public Object get(Object key){
			return redisTemplate.opsForValue().get(key);

	    }

	    /**
	     * 添加缓存
	     * @param key 键
	     * @param value 值
	     * @return true成功 false失败
	     */
	    public boolean set(String key,Object value) {
	        try {
	            redisTemplate.opsForValue().set(key, value);
	            return true;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }

	    }

	/**
	 * 普通缓存放入并设置时间
	 * @param key 键
	 * @param value 值
	 * @param time 时间(秒) time要大于0 如果time小于等于0 将设置无限期
	 * @return true成功 false 失败
	 */
	public boolean set(String key,Object value,long time){
		try {
			if(time>0){
				redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
			}else{
				set(key, value);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 递增
	 * @param key 键
	 * @param delta 要增加几(大于0)
	 * @return
	 */
	public long incr(String key, long delta){
		if(delta<0){
			throw new RuntimeException("递增因子必须大于0");
		}
		return redisTemplate.opsForValue().increment(key, delta);
	}

	/**
	 * 递减
	 * @param key 键
	 * @param delta 要减少几(小于0)
	 * @return
	 */
	public long decr(String key, long delta){
		if(delta<0){
			throw new RuntimeException("递减因子必须大于0");
		}
		return redisTemplate.opsForValue().increment(key, -delta);
	}

	/**
	 * 获取list缓存的内容
	 * @param key 键
	 * @param start 开始
	 * @param end 结束  0 到 -1代表所有值
	 * @return
	 */
	public List<Object> lGet(String key, long start, long end){
		try {
			return redisTemplate.opsForList().range(key, start, end);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public <T> List<T> lGet(String key, long start, long end, Class<T> clazz){
		List<Object> objects = lGet(key, start, end);
		List<T> list = new LinkedList<>();
		for (Object o :objects) {
			list.add(JsonUtil.objToPojo(o,clazz));
		}
		return list;
	}
	/**
	 * 获取list缓存的长度
	 * @param key 键
	 * @return
	 */
	public long lGetListSize(String key){
		try {
			return redisTemplate.opsForList().size(key);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 通过索引 获取list中的值
	 * @param key 键
	 * @param index 索引  index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
	 * @return
	 */
	public Object lGetIndex(String key,long index){
		try {
			return redisTemplate.opsForList().index(key, index);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 将list放入缓存
	 * @param key 键
	 * @param value 值
	 * @return
	 */
	public boolean lSet(String key, Object value) {
		try {
			redisTemplate.opsForList().rightPush(key, value);

				return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 将list放入缓存
	 * @param key 键
	 * @param value 值
	 * @param time 时间(秒)
	 * @return
	 */
	public boolean lSet(String key, Object value, long time) {
		try {
			redisTemplate.opsForList().rightPush(key, value);
			if (time > 0) expire(key, time);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 将list放入缓存
	 * @param key 键
	 * @param value 值
	 * @return
	 */
	public boolean listSet(String key, List value) {
		try {
			//System.out.println(value.size());
			redisTemplate.opsForList().rightPushAll(key, value);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 将list放入缓存
	 * @param key 键
	 * @param value 值
	 * @param time 时间(秒)
	 * @return
	 */
	public boolean listSet(String key, List<Object> value, long time) {
		try {
			redisTemplate.opsForList().rightPushAll(key, value);
			if (time > 0) expire(key, time);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 根据索引修改list中的某条数据
	 * @param key 键
	 * @param index 索引
	 * @param value 值
	 * @return
	 */
	public boolean lUpdateIndex(String key, long index,Object value) {
		try {
			redisTemplate.opsForList().set(key, index, value);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 移除N个值为value
	 * @param key 键
	 * @param count 移除多少个
	 * @param value 值
	 * @return 移除的个数
	 */
	public long lRemove(String key,long count,Object value) {
		try {
			Long remove = redisTemplate.opsForList().remove(key, count, value);
			return remove;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public  String objToString(String key){
		Object obj = get(key);
		if (null!=obj&&!obj.equals("null")) {
			return String.valueOf(obj);
		}else {
			return null;
		}
	}

	public Long objToLong(String key) {
		Object obj = get(key);
		//System.out.println(obj.toString()+"l");
		if (null!=obj&&!obj.equals("null")) {
			return Long.parseLong(String.valueOf(obj));
		}else {
			redisTemplate.opsForValue().set(key,"0");
			return 0l;
		}

	}


	public int objToInt(String key){
		Object round = get(key);
		if(round!=null&&!round.equals("null")) {
			int i = Integer.parseInt(String.valueOf(round));
			return i;
		}else{
			//System.out.println("转换"+key+"为0");
			return 0;
		}
	}

	public Set getKeys(String keys){
	return	redisTemplate.keys(keys);
	}

	public void setMap(String key,Map map){
		redisTemplate.opsForHash().putAll(key,map);
	}

	public Set getMapKey(String key){
		return redisTemplate.opsForHash().keys(key);
	}

	public List getMapAllValue(String key){
		return redisTemplate.opsForHash().values(key);
	}

	public Object getMapValue(String key, String mapKey){
		return redisTemplate.opsForHash().get(key,mapKey);
	}
}
