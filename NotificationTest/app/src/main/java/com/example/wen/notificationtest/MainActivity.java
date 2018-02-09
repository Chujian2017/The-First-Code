package com.example.wen.notificationtest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sendNotice=(Button)findViewById(R.id.send_notice);
        sendNotice.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.send_notice:
                Intent intent=new Intent(this,NotificationActivity.class);
                PendingIntent pi=PendingIntent.getActivity(this,0,intent,0);
                NotificationManager manager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                Notification notification=new NotificationCompat.Builder(this)
                        .setContentTitle("This is content title")
                        .setContentText("This is content text")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                        .setContentIntent(pi)
                        .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(),R.drawable.big_image)))
                        //.setStyle(new NotificationCompat .BigTextStyle().bigText("Learn how to bulid notifications,send and sync data,and use voice actions." +
                                //"Get the official Android IDE and developer tools to bulid apps for Android."))
                        .setPriority(NotificationCompat.PRIORITY_MAX)
                        .setAutoCancel(true)
                        .build();
                manager.notify(1,notification);
                break;
            default:break;
        }
    }
}
