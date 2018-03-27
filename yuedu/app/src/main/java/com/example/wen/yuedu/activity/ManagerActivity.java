package com.example.wen.yuedu.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.wen.yuedu.base.BaseActivity;
import com.example.wen.yuedu.R;
import com.example.wen.yuedu.db.MyDatabaseHelper;
import com.example.wen.yuedu.model.Menus;
import com.example.wen.yuedu.adapter.MenusAdapter;

import java.util.ArrayList;
import java.util.List;

public class ManagerActivity extends BaseActivity {
    private List<Menus> MenuList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql);
        addToolBar();
        initMenu();
        MyDatabaseHelper dHelper=new MyDatabaseHelper(this,"BookStore.db",null,1);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_SQLmenu);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(
                3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        final MenusAdapter adapter = new MenusAdapter(MenuList,ManagerActivity.this);
        recyclerView.setAdapter(adapter);
    }
    private void initMenu(){
        /*Menus bookcase = new Menus("创建SQL",R.drawable.database);
        MenuList.add(bookcase);*/
        Menus bookimport=new Menus("导入书籍",R.drawable.addbook1);
        MenuList.add(bookimport);
        /*Menus bookmanager = new Menus("创建书籍",R.drawable.addbook2);
        MenuList.add(bookmanager);*/
        Menus delete=new Menus("删除书籍",R.drawable.delete);
        MenuList.add(delete);
    }
}
