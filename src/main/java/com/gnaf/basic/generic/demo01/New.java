package com.gnaf.basic.generic.demo01;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Derek
 * @date 2019/10/20 10:21
 */
public class New {

    public static <K, V> Map<K,V> map() {
        return new HashMap<K, V>();
    }

}
