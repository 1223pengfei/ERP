package com.scd.erp.utils.plm;

import com.scd.erp.constant.ConstantMaterials;
import com.scd.erp.utils.common.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class numberUtil {

    @Autowired
    private RedisUtils redisUtils;

    public String setERPnum(String prefix) {

        String key = "erpnum_" + prefix;
        int temp = redisUtils.objToInt(key);
        temp++;
        redisUtils.set(key, temp);
        String l = temp + "";
        StringBuffer sb = new StringBuffer();
        sb.append(prefix);
        for (int i = 0; i < 7 - l.length(); i++) {
            sb.append(0);
        }
        sb.append(temp);
        return sb.toString();
    }

    public String setVersion(String erpnum) {
        int i = redisUtils.objToInt(ConstantMaterials.KEYS[6] + erpnum);
        return (i++)+"";
    }
}
