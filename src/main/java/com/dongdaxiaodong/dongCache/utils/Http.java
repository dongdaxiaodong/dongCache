package com.dongdaxiaodong.dongCache.utils;

public class Http {
    public static String[] response = new String[]{"<html>","<body>","","</body>","</html>"};

    public static String StrArrToStr(){
        StringBuilder sb = new StringBuilder();
        for(String str:response) sb.append(str);
        return sb.toString();
    }
}
