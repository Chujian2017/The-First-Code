package com.example.wen.yuedu;


import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;



import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BookActivity extends AppCompatActivity {
    private List<Book> fruitList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
        initFruits();
        RecyclerView recyclerView=(RecyclerView) findViewById(R.id.recycler_view);
        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(
                3,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        BookAdapter adapter=new BookAdapter(fruitList);
        recyclerView.setAdapter(adapter);
        Button back=(Button)findViewById(R.id.title_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void initFruits(){
        for(int i=0;i<2;i++){
            Book apple=new Book(getRandomLengthName("Apple"),R.drawable.apple_pic);
            fruitList.add(apple);
            Book banana=new Book(getRandomLengthName("Banana"),R.drawable.banana_pic);
            fruitList.add(banana);
            Book orange=new Book(getRandomLengthName("Orange"),R.drawable.orange_pic);
            fruitList.add(orange);
            Book watermelon=new Book(getRandomLengthName("Watermelon"),R.drawable.watermelon_pic);
            fruitList.add(watermelon);
            Book pear=new Book(getRandomLengthName("Pear"),R.drawable.pear_pic);
            fruitList.add(pear);
            Book grape=new Book(getRandomLengthName("Grape"),R.drawable.grape_pic);
            fruitList.add(grape);
            Book pineapple=new Book(getRandomLengthName("Pineapple"),R.drawable.pineapple_pic);
            fruitList.add(pineapple);
            Book strawberry=new Book(getRandomLengthName("Strawberry"),R.drawable.strawberry_pic);
            fruitList.add(strawberry);
            Book cherry=new Book(getRandomLengthName("Cherry"),R.drawable.cherry_pic);
            fruitList.add(cherry);
            Book mango=new Book(getRandomLengthName("Mango"),R.drawable.mango_pic);
            fruitList.add( mango);

        }
    }
    private String getRandomLengthName(String name){
        Random random=new Random();
        //int length=random.nextInt(20)-1;
        int length=1;
        StringBuilder builder=new StringBuilder();
        for(int i=0;i<length;i++){
            builder.append(name);
        }
        return builder.toString();
    }
}