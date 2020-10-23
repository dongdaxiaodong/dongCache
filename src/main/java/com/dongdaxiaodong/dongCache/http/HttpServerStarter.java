package com.dongdaxiaodong.dongCache.http;

import com.dongdaxiaodong.dongCache.ByteView;
import com.dongdaxiaodong.dongCache.DongCache;
import com.dongdaxiaodong.dongCache.Group;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class HttpServerStarter {
    public static void main(String[] args) throws IOException {
        DongCache.NewGroup("scores",2<<10);
        Group  group = DongCache.GetGroup("scores");
        System.out.println(group);
        group.Add("Tom",new ByteView("630".getBytes()));
        group.Add("Jack",new ByteView("589".getBytes()));
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(9100), 0);
        httpServer.createContext(Http.defaultBasePath, new HttpHandler() {
            @Override
            public void handle(HttpExchange httpExchange) throws IOException {
                System.out.println(httpExchange.getRequestURI().getPath());
                String uriPath = httpExchange.getRequestURI().getPath();
                System.out.println(uriPath);
                String[] infoArr = uriPath.split("/");
                String responseContentStr = "";
                if(infoArr.length!=4){
                    com.dongdaxiaodong.dongCache.utils.Http.response[2] = "parameter wrong";
                }else{
                    String groupName = infoArr[2];
                    String key = infoArr[3];
                    Group  group = DongCache.GetGroup(groupName);
                    if(group==null){
                        com.dongdaxiaodong.dongCache.utils.Http.response[2] = "do not have this group";
                    }else{
                        ByteView value = group.Get(key);
                        if(value==null){
                            com.dongdaxiaodong.dongCache.utils.Http.response[2] = "do not have this key";
                        }else{
                            com.dongdaxiaodong.dongCache.utils.Http.response[2] = value.String();
                        }
                    }
                }
                responseContentStr = com.dongdaxiaodong.dongCache.utils.Http.StrArrToStr();
                byte[] responseContentByte = responseContentStr.getBytes("utf-8");
                //设置响应头，必须在sendResponseHeaders方法之前设置！
                httpExchange.getResponseHeaders().add("Content-Type:", "text/html;charset=utf-8");
                //设置响应码和响应体长度，必须在getResponseBody方法之前调用！
                httpExchange.sendResponseHeaders(200, responseContentByte.length);
                OutputStream out = httpExchange.getResponseBody();
                out.write(responseContentByte);
                out.flush();
                out.close();
            }
        });
        httpServer.setExecutor(Executors.newFixedThreadPool(10));
        httpServer.start();
    }
}
