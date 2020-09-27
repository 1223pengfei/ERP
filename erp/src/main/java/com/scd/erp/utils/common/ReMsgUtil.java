package com.scd.erp.utils.common;

import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class ReMsgUtil {

    private static final String[] RECODE = "200,404,500".split(",");
    private Map map ;

    public ReMsgUtil() {
        this.map = new LinkedHashMap();
    }

    public Map msg(Object data, String msg, Integer i){

        map.put("data",data);
        map.put("msg",msg);
        map.put("code",RECODE[i]);
        return map;
    }

}
