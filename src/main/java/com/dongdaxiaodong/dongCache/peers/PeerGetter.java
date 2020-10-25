package com.dongdaxiaodong.dongCache.peers;

import java.io.IOException;

public interface PeerGetter {
    byte[] Get(String group,String key) throws IOException, InterruptedException;
}
