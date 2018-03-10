package com.example.wen.yuedu.Recycler_book;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

/**
 * Created by wen on 2018/3/5.
 */


public class Book {
    private String name;
    private int bookId;
    private int bookNum;
    private BitmapDrawable bitmap;
    public Book(String name,int bookId,int bookNum,BitmapDrawable bitmap){
        this.name=name;
        this.bookId=bookId;
        this.bookNum=bookNum;
        this.bitmap=bitmap;
    }
    public String getName(){
        return name;
    }
    public int getImageId(){
        return bookId;
    }
    public int getNum(){
        return bookNum;
    }
    public BitmapDrawable getBitmap(){return bitmap;}
}