package com.example.wen.yuedu.Recycler_main;

/**
 * Created by wen on 2018/3/6.
 */

public class Menus {
    private String name;
    private int MenuId;
    public Menus(String name,int MenuId){
        this.name=name;
        this.MenuId=MenuId;
    }
    public String getName(){
        return name;
    }
    public int getImageId(){
        return MenuId;
    }
}
