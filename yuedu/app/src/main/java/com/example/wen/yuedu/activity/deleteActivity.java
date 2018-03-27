package com.example.wen.yuedu.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.wen.yuedu.base.BaseActivity;
import com.example.wen.yuedu.R;
import com.example.wen.yuedu.model.Book;
import com.example.wen.yuedu.adapter.deleteBookAdapter;
import com.example.wen.yuedu.db.MyDatabaseHelper;

import java.util.List;


public class deleteActivity extends BaseActivity{
    MyDatabaseHelper dHelper;
    deleteBookAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        addToolBar();
        final MyDatabaseHelper dHelper;
        dHelper=new MyDatabaseHelper(this,"BookStore.db",null,1);


        BookActivity bookActivity = new BookActivity(dHelper);
        bookActivity.initBooks();
        List<Book> list = bookActivity.getList();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(
                3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new deleteBookAdapter(list,deleteActivity.this);
        recyclerView.setAdapter(adapter);
    }


}
