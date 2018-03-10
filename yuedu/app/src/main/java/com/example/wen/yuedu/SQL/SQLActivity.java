package com.example.wen.yuedu.SQL;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.wen.yuedu.Base.BaseActivity;
import com.example.wen.yuedu.R;
import com.example.wen.yuedu.Recycler_main.Menus;
import com.example.wen.yuedu.Recycler_main.MenusAdapter;

import java.util.ArrayList;
import java.util.List;

public class SQLActivity extends BaseActivity {
    private List<Menus> MenuList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql);
        addToolBar();
        initMenu();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_SQLmenu);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(
                3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        final MenusAdapter adapter = new MenusAdapter(MenuList,SQLActivity.this);
        recyclerView.setAdapter(adapter);
    }
    private void initMenu(){
        Menus bookcase = new Menus("创建SQL",R.drawable.database);
        MenuList.add(bookcase);
        Menus bookmanager = new Menus("添加书籍",R.drawable.addbook2);
        MenuList.add(bookmanager);
        Menus delete=new Menus("删除书籍",R.drawable.delete);
        MenuList.add(delete);
    }
}
