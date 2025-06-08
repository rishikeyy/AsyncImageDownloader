package com.example;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ImageDownloader {
    int poolsize;

    ImageDownloader(int poolsize){
        this.poolsize=poolsize;//this is basically Keywords.size()
    }

    List<Future<HttpResponse<String>>> request(ArrayList<String> Keywords,String Access_key){
        ExecutorService executor=Executors.newFixedThreadPool(poolsize);
        List<Future<HttpResponse<String>>> responses=new ArrayList<Future<HttpResponse<String>>>();
        for(String keyword : Keywords){
            String urlString="https://api.unsplash.com/search/photos?query=" +
                           URLEncoder.encode(keyword, StandardCharsets.UTF_8);
//                           "&client_id=" + Access_key;
            Callable<HttpResponse<String>> keyword_search=()->{
                HttpClient client=  HttpClient.newHttpClient();
                HttpRequest httpRequest= HttpRequest.newBuilder()
                                        .header("Authorization","Client-ID "+Access_key)
                                        .uri(URI.create(urlString))
                                        .build();

                HttpResponse httpResponse= client.send(httpRequest,HttpResponse.BodyHandlers.ofString());//Http response
                System.out.println(httpResponse+ keyword);
                return httpResponse;
            };

            Future<HttpResponse<String>>resp= executor.submit(keyword_search);//callable function
            System.out.println(resp);
            responses.add(resp);
    }
    return responses;
    }
}
//implement by both httpclient.async & executor.submit(callable)
