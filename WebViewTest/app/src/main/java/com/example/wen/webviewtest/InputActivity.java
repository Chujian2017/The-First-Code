package com.example.wen.webviewtest;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static java.security.AccessController.getContext;

public class InputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
        final EditText inputWeb=(EditText) findViewById(R.id.input);
        Button button=(Button)findViewById(R.id.get);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String get=inputWeb.getText().toString();
                String webInput="http://"+get;
                Intent intent=new Intent(InputActivity.this,MainActivity.class);
                intent.putExtra("web",webInput);
                startActivity(intent);
            }
        });
        Button titleBack = (Button) findViewById(R.id.title_back);
        Button titleEdit = (Button) findViewById(R.id.title_edit);
        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        titleEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"You clicked Edit button",
                        Toast.LENGTH_SHORT).show();
            }
        });


    }
}
