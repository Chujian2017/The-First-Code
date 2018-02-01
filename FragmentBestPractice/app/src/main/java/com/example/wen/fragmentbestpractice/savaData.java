package com.example.wen.fragmentbestpractice;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wen on 2018/2/1.
 */

public class savaData implements Serializable {
    List<News> newsList=new ArrayList<>();
    public List<News> getNewsList(){return newsList;}
    public void setNewsList(List<News> title){
        this.newsList=title;
    }
}
