package com.example.wen.androidmvpdemo.Model;

/**
 * Created by wen on 2018/3/13.
 *  Class Note:模拟登陆的操作的接口，实现类为LoginModelImpl.相当于MVP模式中的Model层
 */

public interface LoginModel {
    void login(String username, String password, OnLoginFinishedListener listener);
}
