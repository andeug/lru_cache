/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lru.cache;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author asimiyu
 */
public class Cache {

    int cacheSize;

    Set<Integer> cacheKeys;
    Map<Integer, Integer> cacheValueMap = new HashMap<>();

    public Cache(int cacheSize) {
        this.cacheSize = cacheSize;
        cacheKeys = new LinkedHashSet<>(cacheSize);
    }

    int get(int key) {
        //if exist return value else -1
        if (cacheKeys.contains(key)) {
            sortKeyTop(key);
            return cacheValueMap.get(key);
        }
        return -1;

    }

    void set(int key, int value) {
        //check if size of cache is maxed out
        if (cacheKeys.size() == cacheSize) {
            cacheKeys.remove(cacheKeys.iterator().next());//removes firstKey
            removeValueInMap(key);
        }

        if (cacheKeys.contains(key)) {
            //find value in map and update
            putValueInMap(key, value);
            sortKeyTop(key);//sort key list
        } else {
            cacheKeys.add(key);
            putValueInMap(key, value);
        }
    }

    void removeValueInMap(int key) {
        cacheValueMap.remove(key);
    }

    void putValueInMap(int key, int value) {
        cacheValueMap.put(key, value);
    }

    private void sortKeyTop(int key) {
        cacheKeys.remove(key);
        cacheKeys.add(key);
    }

}
