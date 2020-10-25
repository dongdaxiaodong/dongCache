package com.dongdaxiaodong.dongCache.peers;

public interface PeerPicker {
    PeerGetter PickPeer(String key);
}


