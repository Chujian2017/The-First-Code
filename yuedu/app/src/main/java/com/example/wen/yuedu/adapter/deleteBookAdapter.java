package com.example.wen.yuedu.adapter;

/**
 * Created by wen on 2018/3/9.
 */


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
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
import com.example.wen.yuedu.model.Book;
import com.example.wen.yuedu.db.MyDatabaseHelper;

import java.util.List;

import static android.content.ContentValues.TAG;



public class deleteBookAdapter extends RecyclerView.Adapter<deleteBookAdapter.ViewHolder> {
    private List<Book> mBookList;
    MyDatabaseHelper dHelper;
    private Context context;
    public deleteBookAdapter(List<Book> List, Context context){
        this.context=context;
        this.mBookList=List;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View bookView;
        ImageView bookImage;
        TextView bookName;
        ProgressBar progressBar;

        public ViewHolder(View view) {
            super(view);
            bookView = view;
            bookImage = (ImageView) view.findViewById(R.id.book_image);
            bookName = (TextView) view.findViewById(R.id.book_name);
            progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        }
    }

    public deleteBookAdapter(List<Book> bookList) {
        mBookList = bookList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        dHelper=new MyDatabaseHelper(context,"BookStore.db",null,1);

        holder.bookView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Book fruit = mBookList.get(position);
                SQLiteDatabase db=dHelper.getWritableDatabase();
                Log.d(TAG, String.valueOf(fruit.getBookId()));
                String[]bookId={String.valueOf(fruit.getBookId())};
                db.delete("Book","id=?",bookId);
                Toast.makeText(view.getContext(), "删除书籍:" + fruit.getName()+" 成功！", Toast.LENGTH_SHORT).show();


            }
        });
        holder.bookImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Book fruit = mBookList.get(position);
                SQLiteDatabase db=dHelper.getWritableDatabase();
                String[]bookId={String.valueOf(fruit.getBookId())};
                db.delete("Book","id=?",bookId);
                Toast.makeText(view.getContext(), "删除书籍:" + fruit.getName()+" 成功！", Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Book book = mBookList.get(position);
        holder.bookImage.setImageBitmap(book.getBitmap().getBitmap());

        holder.bookName.setText(book.getName());
        Log.d(TAG, book.getName());
        holder.progressBar.setMax(book.getNum());
        holder.progressBar.setProgress(book.getReadNum());
    }

    @Override
    public int getItemCount() {
        return mBookList.size();
    }

}