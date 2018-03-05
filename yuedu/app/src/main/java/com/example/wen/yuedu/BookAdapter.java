package com.example.wen.yuedu;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;




import java.util.List;

/**
 * Created by wen on 2018/3/5.
 */

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    private List<Book> mFruitList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        View bookView;
        ImageView bookImage;
        TextView bookName;
        public ViewHolder(View view){
            super(view);
            bookView=view;
            bookImage=(ImageView)view.findViewById(R.id.book_image);
            bookName=(TextView)view.findViewById(R.id.book_name);
        }
    }
    public BookAdapter(List<Book> bookList){
        mFruitList=bookList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.bookView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int  position=holder.getAdapterPosition();
                Book fruit=mFruitList.get(position);
                Toast.makeText(view.getContext(),"You clicked view "+fruit.getName(),Toast.LENGTH_SHORT).show();
            }
        });
        holder.bookImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int position=holder.getAdapterPosition();
                Book fruit=mFruitList.get(position);
                Toast.makeText(view.getContext(),"You Clicked image "+fruit.getName(),Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Book fruit=mFruitList.get(position);
        holder.bookImage.setImageResource(fruit.getImageId());
        holder.bookName.setText(fruit.getName());
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }
}