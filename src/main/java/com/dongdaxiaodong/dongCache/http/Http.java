package com.dongdaxiaodong.dongCache.http;



public class Http {
    static String defaultBasePath = "/dongCache/";
    static int defaultReplicas = 50;
    public Http(){};

    public HttpPool NewHttpPool(String self){
        return new HttpPool(self,defaultBasePath);
    }
}
