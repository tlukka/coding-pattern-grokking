package systemdesign;

import java.util.LinkedHashMap;

class LRUCacheWithLinkedList {

    int cap;
    LinkedHashMap<Integer, Integer> cache;


    public LRUCacheWithLinkedList(int capacity) {
        cap = capacity;
        cache = new LinkedHashMap<>();

    }

    public int get(int key) {
        if(!cache.containsKey(key))
            return -1;
        int val = cache.get(key);
        cache.remove(key);
        cache.put(key, val);
        return val;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            cache.remove(key);
            cache.put(key,value);
            return;
        }
        if (cache.size() >= cap) {
            Integer oldestKey = cache.keySet().iterator().next();
            cache.remove(oldestKey);
        }
        cache.put(key,value);
    }
}