package com.example.wen.yuedu.model;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

/**
 * Created by wen on 2018/3/5.
 */


public class Book {
    private String name;
    private int bookId;
    private int bookNum;
    private int readNum;
    private BitmapDrawable bitmap;
    private String path;
    public Book(String name,int bookId,int bookNum,int readNum,BitmapDrawable bitmap,String path){
        this.name=name;
        this.bookId=bookId;
        this.bookNum=bookNum;
        this.bitmap=bitmap;
        this.readNum=readNum;
        this.path=path;


    }
    public String getName(){
        return name;
    }
    public int getBookId(){
        return bookId;
    }
    public int getNum(){
        return bookNum;
    }
    public BitmapDrawable getBitmap(){return bitmap;}
    public int getReadNum(){return readNum;}
    public String getUri(){return path;}
}