package com.dongdaxiaodong.dongCache.http;

public class HttpPool {
    String self;
    String basePath;
    public HttpPool(){};
    public HttpPool(String self,String basePath){
        this.self = self;
        this.basePath = basePath;
    }
}
