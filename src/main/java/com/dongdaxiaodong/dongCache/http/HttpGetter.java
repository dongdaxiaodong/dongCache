package com.dongdaxiaodong.dongCache.http;


import com.dongdaxiaodong.dongCache.peers.PeerGetter;

import java.io.IOException;
import java.net.CookieManager;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class HttpGetter implements PeerGetter {
    String baseUrl;

    public HttpGetter(){};
    public HttpGetter(String baseUrl){
        this.baseUrl = baseUrl;
    }
    @Override
    public byte[] Get(String group,String key) throws IOException, InterruptedException {
        HttpRequest.BodyPublisher publisher = HttpRequest.BodyPublishers.ofString("some body text");
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .connectTimeout(Duration.ofSeconds(2))
                .cookieHandler(new CookieManager())
                .build();
        HttpRequest request = HttpRequest.newBuilder().
                version(HttpClient.Version.HTTP_1_1).
                method("GET",publisher).
                timeout(Duration.ofSeconds(2)).
                uri(URI.create(this.baseUrl+group+"/"+key)).
                build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body().getBytes();
    }
}
