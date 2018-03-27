package com.example.wen.mvphelloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements MainView{
    private TextView tv;
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);
        presenter = new MainPresenter(this);
        presenter.presenter("world!");
    }


    @Override
    public void setView(String a) {
        tv.setText(a);
    }
    }

