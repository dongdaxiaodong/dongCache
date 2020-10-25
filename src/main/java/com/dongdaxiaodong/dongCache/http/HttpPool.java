package com.dongdaxiaodong.dongCache.http;

import com.dongdaxiaodong.dongCache.consistenthash.Map;
import com.dongdaxiaodong.dongCache.peers.PeerGetter;
import com.dongdaxiaodong.dongCache.peers.PeerPicker;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class HttpPool implements PeerPicker {
    String self;
    String basePath;
    ReentrantLock mu;
    Map peers;
    HashMap<String,HttpGetter> httpGetters;
    public HttpPool(){};
    public HttpPool(String self,String basePath){
        this.self = self;
        this.basePath = basePath;
    }

    public void Set(String ... peers){
        this.mu.lock();
        try {
            this.peers = Map.New(Http.defaultReplicas,null);
            this.peers.Add(peers);
            this.httpGetters = new HashMap<String,HttpGetter>();
            for(String str : peers){
                this.httpGetters.put(str,new HttpGetter(str+this.basePath));
            }
        }
        catch (Exception e){}
        finally{ this.mu.unlock();}
    }

    public PeerGetter PickPeer(String key){
        this.mu.lock();
        HttpGetter  peerGetter = null;
        try {
            String peer = this.peers.Get(key);
            if(!peer.equals("") && !peer.equals(this.self)){
                peerGetter = this.httpGetters.get(peer);
            }
        }
        catch (Exception e){}
        finally { this.mu.unlock();}
        return peerGetter;
    }
}
