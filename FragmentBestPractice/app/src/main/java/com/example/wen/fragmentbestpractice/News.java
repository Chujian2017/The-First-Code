package com.example.wen.fragmentbestpractice;

import android.view.View;
import java.io.Serializable;

/**
 * Created by wen on 2018/1/31.
 */

public class News implements Serializable{
    private String title;
    private String content;
    public String getTitle(){
        return title;
    }
    public String getContent(){return content;}
    public void setTitle(String title){
        this.title=title;
    }
    public void setContent(String content){this.content=content;}

}
