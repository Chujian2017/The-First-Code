package com.example.wen.androidmvpdemo.Model;

/**
 * Created by wen on 2018/3/13.
 */

public interface OnLoginFinishedListener {
    void onUsernameError();

    void onPasswordError();

    void onSuccess();
}
