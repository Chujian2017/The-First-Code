package com.example.wen.logintest.receiver;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.util.Log;



public class RestartAppReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals("restart.app")){
                Log.e("TAG","接受到广播");
                Log.e("TAG", Process.myPid()+"");
              Intent i = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(i);
            }
    }
}
