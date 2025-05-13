package com.example;

import java.util.ArrayList;
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
        Future<> Resp=imageDownloader.request(Keywords,Access_key);//save the response of  this downloadmanager to local
    }
}
