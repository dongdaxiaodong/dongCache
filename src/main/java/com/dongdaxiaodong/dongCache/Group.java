package com.dongdaxiaodong.dongCache;

public class Group {
    String name;
    Cache mainCache;
    Group(){};
    Group(String name,long cacheBytes){
        this.name = name;
        this.mainCache = new Cache(cacheBytes);
    }

    public ByteView Get(String key){
        if(key.equals("")){
            return new ByteView();
        }
        ByteView  value = this.mainCache.get(key);
        if(value!=null){
            System.out.println("[DongCache] hit");
            return value;
        }
        return null;
    }
    public void Add(String key,ByteView value){
        this.mainCache.add(key,value);
    }

}
