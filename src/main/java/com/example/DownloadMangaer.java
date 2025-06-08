package com.example;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class DownloadMangaer {
    public ArrayList<String> Keywords;
    protected  ImageDownloader imageDownloader;
    protected String Access_key="H_l7Bijj4lrxtue-qE7b_SLJ58Sddmu70aeRRIpYWrY";
    DownloadMangaer(ArrayList<String> Keywords){
        this.Keywords=Keywords;
    }
    public void startDownload(){
        imageDownloader=new ImageDownloader(Keywords.size());
        //Get the response for each keyword search
        List<Future<HttpResponse<String>>> Resp=imageDownloader.request(Keywords,Access_key);
        for(Future<HttpResponse<String>> u : Resp) {
            try{
                String JsonResponseString=u.get().body();
                ObjectMapper mapper= new ObjectMapper();
                String imageUrl=mapper.readTree(JsonResponseString)
                        .path("results")
                        .get(0)
                        .path("urls")
                        .path("regular")
                        .asText();

                System.out.println("extracted regular quality urls\n\n"+imageUrl);//check if the imageUrl is correct

                // download the image using the imageUrl
                //use executor service to download images in ||el
                //Resumable Download  HTTP HEAD method:

            } catch(InterruptedException | ExecutionException | JsonProcessingException e){
                e.printStackTrace();
            }
        }

    }
}
