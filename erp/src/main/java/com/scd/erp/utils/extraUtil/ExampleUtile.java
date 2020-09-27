package com.scd.erp.utils.extraUtil;

import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@Component
public class ExampleUtile {

    public <T> Example getExample(Class<T> entityClass, Map map ) {
        Example ex = getExample(entityClass);
        Example.Criteria criteria = ex.createCriteria();
        Set set = map.keySet();
        Iterator ite = set.iterator();
        while (ite.hasNext()) {
            Object next = ite.next();
            String key = String.valueOf(next);
            Object value = map.get(next);
            isThis(criteria, key, value);
        }
        return ex;
    }

    private void isThis(Example.Criteria criteria, String key, Object value) {
        criteria.andEqualTo(key,value);
    }
    private void LikeThis(Example.Criteria criteria, String key, Object value) {
        criteria.andLike(key,String.valueOf(value));
    }

    public <T> Example getExample(Class<T> entityClass){
        return new Example(entityClass);
    }

}
