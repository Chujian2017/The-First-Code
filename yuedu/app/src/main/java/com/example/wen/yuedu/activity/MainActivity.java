package com.example.wen.yuedu.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import com.example.wen.yuedu.base.BaseActivity;
import com.example.wen.yuedu.R;
import com.example.wen.yuedu.adapter.MenusAdapter;
import com.example.wen.yuedu.model.Menus;


import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity {
    private List<Menus> MenuList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addToolBar();
        initMenu();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_menu);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(
                3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        final MenusAdapter adapter = new MenusAdapter(MenuList,MainActivity.this);
        recyclerView.setAdapter(adapter);

    }
    private void initMenu(){
        Menus bookcase = new Menus("我的书架",R.drawable.bookcase);
        MenuList.add(bookcase);
        Menus bookmanager = new Menus("书籍管理",R.drawable.add);
        MenuList.add(bookmanager);
        Menus bookget = new Menus("联网获取",R.drawable.net);
        MenuList.add(bookget);
        Menus exit= new Menus("退出",R.drawable.exit);
        MenuList.add(exit);

    }

}