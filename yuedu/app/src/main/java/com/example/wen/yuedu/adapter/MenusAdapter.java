package com.example.wen.yuedu.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wen.yuedu.activity.AddPDFActivity;
import com.example.wen.yuedu.model.Menus;
import com.example.wen.yuedu.db.MyDatabaseHelper;
import com.example.wen.yuedu.R;
import com.example.wen.yuedu.activity.BookActivity;
import com.example.wen.yuedu.activity.ManagerActivity;

import com.example.wen.yuedu.activity.deleteActivity;

import java.util.List;




public class MenusAdapter extends RecyclerView.Adapter<MenusAdapter.ViewHolder> {
    private MyDatabaseHelper dbHelper;
    private List<Menus> mMenuList;
    private Context context;
    private int id;
    static class ViewHolder extends RecyclerView.ViewHolder{
        View MenuView;
        ImageView MenuImage;
        TextView MenuName;
         ViewHolder(View view){
            super(view);
            MenuView=view;
            MenuImage=(ImageView)view.findViewById(R.id.menu_image);
            MenuName=(TextView)view.findViewById(R.id.menu_name);
        }
    }
    public MenusAdapter(List<Menus> MenuList, Context context){
        this.context=context;
        this.mMenuList=MenuList;
    }

    @Override
    public MenusAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.menus_item,parent,false);
        final MenusAdapter.ViewHolder holder=new MenusAdapter.ViewHolder(view);
        holder.MenuView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int  position=holder.getAdapterPosition();
                Menus fruit=mMenuList.get(position);
                switch (fruit.getImageId()){
                    case R.drawable.bookcase:
                        Intent intent=new Intent(context,BookActivity.class);
                        context.startActivity(intent);
                        break;
                    case R.drawable.add:
                        Intent intent2=new Intent(context,ManagerActivity.class);
                        context.startActivity(intent2);
                        break;
                    case R.drawable.exit:
                        System.exit(1);
                        break;
                    case R.drawable.net:
                        Toast.makeText(context,"施工ing",Toast.LENGTH_SHORT).show();
                        break;
                    case R.drawable.delete:
                        Intent intent4=new Intent(context,deleteActivity.class);
                        context.startActivity(intent4);
                        break;
                    case R.drawable.addbook1:
                        Intent intent5=new Intent(context, AddPDFActivity.class);
                        context.startActivity(intent5);
                        break;
                }
            }
        });
        holder.MenuImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int  position=holder.getAdapterPosition();
                Menus fruit=mMenuList.get(position);
                switch (fruit.getImageId()){
                    case R.drawable.bookcase:
                        Intent intent=new Intent(context,BookActivity.class);
                        context.startActivity(intent);
                        break;
                    case R.drawable.add:
                        Intent intent2=new Intent(context,ManagerActivity.class);
                        context.startActivity(intent2);
                        break;
                    case R.drawable.exit:
                        System.exit(1);
                        break;

                    case R.drawable.net:
                        Toast.makeText(context,"施工ing",Toast.LENGTH_SHORT).show();
                        break;
                    case R.drawable.delete:
                        Intent intent4=new Intent(context,deleteActivity.class);
                        context.startActivity(intent4);
                       break;
                    case R.drawable.addbook1:
                        Intent intent5=new Intent(context, AddPDFActivity.class);
                        context.startActivity(intent5);
                        break;


            }
        }});
        return holder;
    }

    @Override
    public void onBindViewHolder(MenusAdapter.ViewHolder holder, int position) {
        Menus menus=mMenuList.get(position);
        holder.MenuImage.setImageResource(menus.getImageId());
        holder.MenuName.setText(menus.getName());
    }

    @Override
    public int getItemCount() {
        return mMenuList.size();
    }


}
