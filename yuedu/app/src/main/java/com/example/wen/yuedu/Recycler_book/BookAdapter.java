package com.example.wen.yuedu.Recycler_book;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.wen.yuedu.R;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by wen on 2018/3/5.
 */

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    private List<Book> mBookList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        View bookView;
        ImageView bookImage;
        TextView bookName;
        ProgressBar progressBar;
        public ViewHolder(View view){
            super(view);
            bookView=view;
            bookImage=(ImageView)view.findViewById(R.id.book_image);
            bookName=(TextView)view.findViewById(R.id.book_name);
            progressBar=(ProgressBar)view.findViewById(R.id.progress_bar);
        }
    }
    public BookAdapter(List<Book> bookList){
        mBookList=bookList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.bookView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int  position=holder.getAdapterPosition();
                Book fruit=mBookList.get(position);
                int progress=holder.progressBar.getProgress();
                progress=10+progress;
                holder.progressBar.setProgress(progress);
                Toast.makeText(view.getContext(),"You clicked view "+fruit.getName(),Toast.LENGTH_SHORT).show();
            }
        });
        holder.bookImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int position=holder.getAdapterPosition();
                Book fruit=mBookList.get(position);
                int progress=holder.progressBar.getProgress();
                progress=10+progress;
                holder.progressBar.setProgress(progress);
                Toast.makeText(view.getContext(),"You Clicked image "+fruit.getName(),Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Book book = mBookList.get(position);
        if (book.getImageId() != -1) {
            holder.bookImage.setImageResource(book.getImageId());
        } else {
            holder.bookImage.setImageBitmap(book.getBitmap().getBitmap());
        }
            holder.bookName.setText(book.getName());
        Log.d(TAG, book.getName());
            holder.progressBar.setMax(book.getNum());
        }

        @Override
        public int getItemCount() {
            return mBookList.size();
        }

}