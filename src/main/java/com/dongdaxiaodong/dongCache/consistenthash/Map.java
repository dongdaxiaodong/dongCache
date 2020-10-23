package com.dongdaxiaodong.dongCache.consistenthash;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.CRC32;

public class Map{
    Hash hash;
    int replicas;
    List<Integer> keys;
    HashMap<Integer,String> hashMap;

    public Map(){};
    public Map(int replicas,Hash fn){
        this.replicas = replicas;
        this.hash = fn;
    }
    static Map New(int replicas,Hash fn){
        Map newMap = new Map(replicas,fn);
        if(fn == null){
            newMap.hash = data -> {
                CRC32 c = new CRC32();
                c.reset();
                c.update(data,0,data.length);
                return c.getValue();
            };
        }
        return newMap;
    }

    public void Add(String ... keys){
        for(String key : keys) {
            for(int i=0;i<replicas;i+=1){
                int hashValue = (int)(hash.hash((i+key).getBytes()));
                this.keys.add(hashValue);
                this.hashMap.put(hashValue,key);
            }
        }
        this.keys=this.keys.stream().sorted().collect(Collectors.toList());
    }

    public String Get(String key){
        if(this.keys.size()==0){
            return "";
        }
        int hashValue = (int)(hash.hash(key.getBytes()));
        int index = binarySearch(hashValue);
        if(index==this.keys.size()) index = 0;
        return this.hashMap.get(this.keys.get(index));
    }

    public int binarySearch(int hashValue){
        int start = 0;
        int end = this.keys.size();
        int middle = 0;
        while(start<end){
            middle = (start+end)/2;
            if(this.keys.get(middle)<hashValue){
                start = middle+1;
            }
            else{
                end = middle;
            }
        }
        return start;
    }
}
