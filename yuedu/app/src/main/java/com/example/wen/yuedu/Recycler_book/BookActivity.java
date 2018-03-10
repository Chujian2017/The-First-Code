package com.example.wen.yuedu.Recycler_book;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import com.example.wen.yuedu.Base.BaseActivity;
import com.example.wen.yuedu.R;
import com.example.wen.yuedu.SQL.MyDatabaseHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BookActivity extends BaseActivity {
    private List<Book> fruitList = new ArrayList<>();
    MyDatabaseHelper dHelper;
    public BookActivity(){}
    public BookActivity(MyDatabaseHelper dbHelper){
        this.dHelper=dbHelper;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        addToolBar();
        dHelper=new MyDatabaseHelper(this,"BookStore.db",null,1);

        initBooks();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(
                3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        BookAdapter adapter = new BookAdapter(fruitList);
        recyclerView.setAdapter(adapter);

    }

    public void initBooks() {
        SQLiteDatabase db = dHelper.getWritableDatabase();
        String[] columns = {"bookName", "image", "bookNum"};
        Cursor cursor = db.query("Book", null, null, null, null, null, null);
        if(cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex("bookName"));
                int bookNum = cursor.getInt(cursor.getColumnIndex("bookNum"));
                byte[] blob = cursor.getBlob(cursor.getColumnIndex("image"));
                Bitmap bmp = BitmapFactory.decodeByteArray(blob, 0, blob.length);
                BitmapDrawable bd = new BitmapDrawable(bmp);
                Book book = new Book(name, -1, bookNum, bd);
                fruitList.add(book);
            } while (cursor.moveToNext());
        }cursor.close();
        for (int i = 0; i < 2; i++) {
            Book apple = new Book(getRandomLengthName("Apple"), R.drawable.apple_pic, 10,null);
            fruitList.add(apple);
            Book banana = new Book(getRandomLengthName("Banana"), R.drawable.banana_pic, 10,null);
            fruitList.add(banana);
            Book orange = new Book(getRandomLengthName("Orange"), R.drawable.orange_pic, 10,null);
            fruitList.add(orange);
            Book watermelon = new Book(getRandomLengthName("Watermelon"), R.drawable.watermelon_pic, 10,null);
            fruitList.add(watermelon);
            Book pear = new Book(getRandomLengthName("Pear"), R.drawable.pear_pic, 10,null);
            fruitList.add(pear);
            Book grape = new Book(getRandomLengthName("Grape"), R.drawable.grape_pic, 20,null);
            fruitList.add(grape);
            Book pineapple = new Book(getRandomLengthName("Pineapple"), R.drawable.pineapple_pic, 25,null);
            fruitList.add(pineapple);
            Book strawberry = new Book(getRandomLengthName("Strawberry"), R.drawable.strawberry_pic, 30,null);
            fruitList.add(strawberry);
            Book cherry = new Book(getRandomLengthName("Cherry"), R.drawable.cherry_pic, 40,null);
            fruitList.add(cherry);
            Book mango = new Book(getRandomLengthName("Mango"), R.drawable.mango_pic, 50,null);
            fruitList.add(mango);

        }
    }

    private String getRandomLengthName(String name) {
        Random random = new Random();
        //int length=random.nextInt(20)-1;
        int length = 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(name);
        }
        return builder.toString();
    }
    public List<Book> getList(){
        return fruitList;
    }

}