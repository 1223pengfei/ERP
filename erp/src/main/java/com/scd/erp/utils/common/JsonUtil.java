package com.scd.erp.utils.common;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;
import java.util.*;

public class JsonUtil {

    public static <T> List<T> StringToVoList(String json, Class<T> clazz) throws IOException {
        ObjectMapper om = new ObjectMapper();
        JsonNode tree = om.readTree(json);
        Iterator<JsonNode> ele = tree.elements();
        List list = new ArrayList<>();
        while (ele.hasNext()) {
            JsonNode next = ele.next();
            ObjectReader objectReader = om.readerFor(clazz);
            Object value = objectReader.readValue(next);
            list.add(om.convertValue(value, clazz));
        }
        return list;
    }

    public static List StringToList(String json) throws Throwable {
        ObjectMapper om = new ObjectMapper();
        JsonNode tree = om.readTree(json);
        Iterator<JsonNode> ele = tree.elements();
        List list = new ArrayList<>();
        while (ele.hasNext()) {
            list.add(ele.next());
        }
        return list;
    }


    public static Map JsonToMap(String json) {
        Map map = new HashMap<>();
        ObjectMapper om = new ObjectMapper();

        try {
            map = om.readValue(json, Map.class);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }


    /**
     * 将对象转换为json格式字符串
     *
     * @param obj
     * @return json string
     */
    public static String toJSON(Object obj) {
        ObjectMapper om = new ObjectMapper();
        try {
            String json = om.writeValueAsString(obj);
            return json;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将json形式字符串转换为java实体类
     */
    public static <T> T parse(String jsonStr, Class<T> clazz) {
        ObjectMapper om = new ObjectMapper();
        T readValue = null;
        try {
            readValue = om.readValue(jsonStr, clazz);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return readValue;
    }

    public static <T> T objToPojo(Object object, Class<T> clazz) {
        ObjectMapper om = new ObjectMapper();
        return om.convertValue(object, clazz);
    }
}
