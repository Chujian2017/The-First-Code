package com.example.wen.fragmentbestpractice;

import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NewsContentActivity extends AppCompatActivity {
    private List<News> news=new ArrayList<>();


    public static void actionStart(Context context, News news){
        Intent intent=new Intent(context,NewsContentActivity.class);
        //savaData savaData=new savaData();
        //savaData.setNewsList(list);
        intent.putExtra("news_title",news);
       // intent.putExtra("new_content",newsContent);
        context.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_content);
        News news=(News)getIntent().getSerializableExtra("news_title");
        TextView newTitle=(TextView)findViewById(R.id.news_title);
        newTitle.setText(news.getTitle());

        //List<News> list=new ArrayList<>();
        //savaData Data=new savaData();
        //Data=(savaData)getIntent().getSerializableExtra("news_title") ;
        //list=Data.getNewsList();
        //save.setTitle(getIntent().getStringExtra("news_title"));
        //save.setContent(getIntent().getStringExtra("new_content"));

        NewsContentFragment newsContentFragment=(NewsContentFragment)
                getSupportFragmentManager().findFragmentById(R.id.news_content_fragment);
        newsContentFragment.refresh(news);
    }

}
