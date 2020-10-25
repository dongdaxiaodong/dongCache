package com.dongdaxiaodong.dongCache;

import com.dongdaxiaodong.dongCache.http.HttpGetter;
import com.dongdaxiaodong.dongCache.peers.PeerGetter;
import com.dongdaxiaodong.dongCache.peers.PeerPicker;

import java.io.IOException;

public class Group {
    String name;
    Cache mainCache;
    PeerPicker peers;
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

    public void RegisterPeers(PeerPicker peers){
        if(this.peers != null){
            System.out.println("more than one");
        }
        this.peers = peers;
    }

    public ByteView load(String key) throws IOException, InterruptedException {
        if(this.peers != null){
            PeerGetter peer =  this.peers.PickPeer(key);
            return this.getFromPeer(peer,key);
        }
        return new ByteView();
    }

    public ByteView getFromPeer(PeerGetter peer, String key) throws IOException, InterruptedException {
        byte[] bytes = peer.Get(this.name,key);
        return new ByteView(bytes);
    }

}
