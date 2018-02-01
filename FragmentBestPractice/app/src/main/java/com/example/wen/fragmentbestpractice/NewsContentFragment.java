package com.example.wen.fragmentbestpractice;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by wen on 2018/1/31.
 */

public class NewsContentFragment extends Fragment {
    private List<News> news=new ArrayList<News>();
    private ContentAdapter adapter = new ContentAdapter(news);
    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.news_content_recycler,container,false);
        //View newview=inflater.inflate(R.layout.news_content_frag,container,false);


        RecyclerView newsTitleRecyclerView = (RecyclerView) view.findViewById(R.id.news_content_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        newsTitleRecyclerView.setLayoutManager(layoutManager);
        newsTitleRecyclerView.setAdapter(adapter);
        return view;
    }

    public void refresh(News newData) {
       news.add(newData);
    }

   // public void refresh(boolean bol, News newData){
        //news.add(newData);
        //TextView newsTitle=(TextView)view.findViewById(R.id.news_title);
        //newsTitle.setText(newData.getTitle());
        //newTitle.setText(newData.getTitle());
   // }

    class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ViewHolder> {
        private List<News> mNewsList;

        class ViewHolder extends RecyclerView.ViewHolder {

            TextView newsContentText;

            public ViewHolder(View view) {
                super(view);
                newsContentText = (TextView) view.findViewById(R.id.news_content);

            }
        }

        public ContentAdapter(List<News> newsList) {
            mNewsList = newsList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_content_frag, parent, false);
            final ViewHolder holder = new ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            News news = mNewsList.get(position);
            holder.newsContentText.setText(news.getContent());
        }

        @Override
        public int getItemCount() {
            return mNewsList.size();
        }
    }
}


