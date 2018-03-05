package com.example.wen.yuedu;

/**
 * Created by wen on 2018/3/5.
 */


public class Book {
    private String name;
    private int bookId;
    private int bookNum;
    public Book(String name,int bookId){
        this.name=name;
        this.bookId=bookId;
    }
    public String getName(){
        return name;
    }
    public int getImageId(){
        return bookId;
    }
}