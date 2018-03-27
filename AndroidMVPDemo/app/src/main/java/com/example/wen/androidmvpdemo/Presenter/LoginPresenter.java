package com.example.wen.androidmvpdemo.Presenter;

/**
 * Created by wen on 2018/3/13.
 *  Class Note:登陆的Presenter 的接口，实现类为LoginPresenterImpl，完成登陆的验证，以及销毁当前view
 */

public interface LoginPresenter {
    void validateCredentials(String username, String password);

    void onDestroy();
}