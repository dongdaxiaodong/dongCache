package com.dongdaxiaodong.dongCache.rejectStrategy;

public interface CallBack {
    void onEvicted(String key,byte[] value);
}
