package com.example;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ImageDownloader {
    int poolsize;
    
    ImageDownloader(int poolsize){
        this.poolsize=poolsize;
    }
    //just for learning purpose return List of Inputstreams
    List<Future<Inputstreams>> request(ArrayList<String> Keywords,String Access_key){
        ExecutorService executor=Executors.newFixedThreadPool(poolsize);
        for(String keyword : Keywords){
            //call API and parse resp and send req for URLs and store in inputStream
            executor.submit(callable);//callable function 
    }
    }
}
