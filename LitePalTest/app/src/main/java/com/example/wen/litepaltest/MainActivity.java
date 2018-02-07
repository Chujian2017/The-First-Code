package com.example.wen.litepaltest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button createDatabase=(Button)findViewById(R.id.create_database);
        Button addData=(Button)findViewById(R.id.add_data);
        Button updateData=(Button)findViewById(R.id.update_data);
        Button deleteButton =(Button)findViewById(R.id.delete_data);
        Button queryButton=(Button)findViewById(R.id.query_data);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LitePal.getDatabase();
            }
        });
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book=new Book();
                book.setName("The Da Vinci Code");
                book.setAuthor("Dan Brown");
                book.setPages(454);
                book.setPrice(16.96);
                book.setPress("Unknow");
                book.save();
            }
        });
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book=new Book();
                book.setPrice(14.95);
                book.setPress("Anchor");
                book.updateAll("name= ? and author= ?","The Lost Symbol","Dan Brown");
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataSupport.deleteAll(Book.class,"price<?","15");
            }
        });
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Book> books=DataSupport.findAll(Book.class);
                for(Book book:books){
                    Log.d(TAG, "book name is  "+book.getName());
                    Log.d(TAG, "book author is  "+book.getAuthor());
                    Log.d(TAG, "book pages is  "+book.getPages());
                    Log.d(TAG, "book price is  "+book.getPrice());
                    Log.d(TAG, "book press is  "+book.getPress());
                }
            }
        });
    }
}
