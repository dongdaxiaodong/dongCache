package com.dongdaxiaodong.dongCache;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;



public  class DongCache {
    static ReentrantReadWriteLock mu = new ReentrantReadWriteLock();
    static HashMap<String,Group> groups = new HashMap<>();

    public DongCache(){};

    public static Group NewGroup(String name,long cacheBytes){
        Group newGroup = null;
        DongCache.mu.writeLock().lock();
        try {
            newGroup = new Group(name,cacheBytes);
            DongCache.groups.put(name,newGroup);
        }
        catch (Exception e){}
        finally {
            DongCache.mu.writeLock().unlock();
        }
        return newGroup;
    }

    public static Group GetGroup(String  name){
        DongCache.mu.readLock().lock();
        Group ret = null;
        try {
            ret = DongCache.groups.get(name);
        }
        catch (Exception e){}
        finally {
            DongCache.mu.readLock().unlock();
        }
        return ret;
    }

}
