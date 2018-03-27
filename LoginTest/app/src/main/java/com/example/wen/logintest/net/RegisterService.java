package com.example.wen.logintest.net;

import android.util.Log;

import com.example.wen.logintest.Config;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

import static android.R.attr.action;
import static com.google.android.gms.internal.zzs.TAG;
import static com.example.wen.logintest.R.drawable.account;
import static com.zhy.http.okhttp.OkHttpUtils.post;


public class RegisterService {
    /*
    * 注册
    * */
    public RegisterService(String url,String action,String account,String pwd,String picBase64,final ISuccessCallback successCallback, final IFailCallback failCallback){
        OkHttpUtils
                .post()
                .url(url)
                .addParams(Config.KEY_ACTION,action)
                .addParams(Config.KEY_ACCOUNT,account)
                .addParams(Config.KEY_PWD,pwd)
                .addParams(Config.KEY_USER_ICON,picBase64)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                            if(null!=failCallback){
                                Log.e(TAG,"注册失败"+e.getMessage());
                                failCallback.onFail(e.getMessage());
                            }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int status = jsonObject.getInt(Config.KEY_STATUS);
                            int errCode = jsonObject.getInt(Config.KEY_ERR_CODE);
                            if(status==1){
                                successCallback.onSuccess(response,id);
                            }else if(status==0){
                                failCallback.onFail(response);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
    }
    public interface ISuccessCallback{
        void onSuccess(String response,int id);
    };
    public interface IFailCallback{
        void onFail(String errCause);
    }
}
