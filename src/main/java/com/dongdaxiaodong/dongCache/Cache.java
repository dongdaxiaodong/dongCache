package com.dongdaxiaodong.dongCache;

import com.dongdaxiaodong.dongCache.rejectStrategy.Lru;

import java.util.concurrent.locks.ReentrantLock;

public class Cache {
    ReentrantLock  lock;
    Lru            lru ;
    long           cacheBytes;

    public Cache(){
        lock = new ReentrantLock();
    };
    public Cache(long cacheBytes){
        this();
        this.cacheBytes = cacheBytes;
    }

    public void add(String key, ByteView value){
        lock.lock();
        try {
            if(lru==null){
                lru = new Lru(cacheBytes);
            }
            lru.Add(key,value.b);
        }
        catch (Exception e){

        }
        finally {
            lock.unlock();
        }
    }
    public ByteView get(String key){
        lock.lock();
        byte[] byteArr = null;
        try {
            if(lru==null) return null;
            byteArr = lru.Get(key);
        }
        catch (Exception e){}
        finally {
            lock.unlock();
        }
        if(byteArr==null) return null;
        return new ByteView(byteArr);
    }
}
