package com.example.wen.yuedu.model;

/**
 * Created by wen on 2018/3/6.
 */

public class Menus {//菜单
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
