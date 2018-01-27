package com.example.wen.activitylifecycletest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NormalActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.normal_layout);
    }

    @Override
    public void onBackPressed() {
        Bundle bundle=new Bundle();
        bundle.putString("data_key","Nice！！！");
        Intent intent=new Intent();
        intent.putExtra("data_return",bundle);
        setResult(RESULT_OK,intent);
        finish();

    }
}
