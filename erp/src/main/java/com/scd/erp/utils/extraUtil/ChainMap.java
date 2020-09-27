package com.scd.erp.utils.extraUtil;

import java.util.LinkedHashMap;
import java.util.Map;

public class ChainMap<K,V> extends LinkedHashMap<K,V> {

    public ChainMap(int initialCapacity) {
        super(initialCapacity);
    }

    public ChainMap() {
        super();
    }

    public ChainMap(Map<? extends K, ? extends V> m) {
        super(m);
    }


    public ChainMap(int initialCapacity, float loadFactor) {
        super(initialCapacity,loadFactor);
    }

    public ChainMap putObj(K key,V value){
        this.put(key, value);
        return this;
    }
}
