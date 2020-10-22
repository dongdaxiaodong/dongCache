package com.dongdaxiaodong.dongCache.rejectStrategy;

import java.util.LinkedHashMap;
import java.util.Map;

public class Lru{
    long maxBytes;
    long nBytes;
    LinkedHashMap<String,byte[]> cache;
    CallBack cb;
    public Lru(){}

    public Lru(Long maxBytes) {
        this.maxBytes = maxBytes;
        cache = new LinkedHashMap<String, byte[]>();
    }

    public Lru(Long maxBytes, CallBack cb){
        this(maxBytes);
        this.cb = cb;
    }


    public byte[] Get(String key){
        byte[] ret = null;
        if(cache.containsKey(key)){
            //存在
            ret = cache.get(key);
            cache.remove(key);
            cache.put(key,ret);
        }
        return ret;
    }

    public void Add(String key,byte[] value){
        //有两个情况，一个是add里面已经有Key了，另一种情况是没有
        if(cache.containsValue(key)){
            byte[] oldEle = cache.get(key);
            cache.remove(key);
            cache.put(key,value);
            nBytes = nBytes+(value.length-oldEle.length);
        } else{
            cache.put(key,value);
            nBytes = nBytes + key.getBytes().length + value.length;
        }
        while(nBytes>maxBytes){
            deleteOldEle();
        }
    }
    private void deleteOldEle(){
        Map.Entry<String,byte[]> dEntry = null;
        for(Map.Entry<String,byte[]> entry : cache.entrySet()){
            dEntry = entry;
            break;
        }
        assert dEntry != null;
        System.out.println("delete key "+dEntry.getKey());
        cache.remove(dEntry.getKey());
        nBytes = nBytes-dEntry.getKey().getBytes().length-dEntry.getValue().length;
        if(this.cb!=null){
            this.cb.onEvicted(dEntry.getKey(),dEntry.getValue());
        }
    }
}
