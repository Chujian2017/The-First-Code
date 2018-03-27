package com.example.wen.mvphelloworld;

/**
 * Created by wen on 2018/3/13.
 */

public class MainPresenter {
    //交互层
    private Model model = new Model();//实例化，调用方法模型
    private MainView mainView;

    public MainPresenter(MainView mainView) {
        this.mainView = mainView;
    }

    public void presenter(String a) {
        String s = model.model("hello", a);
        mainView.setView(s);
    }
}
