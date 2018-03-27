package com.example.wen.yuedu.dialog;

/**
 * Created by wen on 2018/3/11.
 */

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.wen.yuedu.R;
import com.example.wen.yuedu.db.MyDatabaseHelper;

public class InputDialog extends AlertDialog implements OnClickListener {
    private EditText etPassword;  //编辑框
    private EditText etName;
    private Button btnConfrim, btnCancel;  //确定取消按钮
    private OnEditInputFinishedListener mListener; //接口
    private int id;
    private int pages;
    private String name;

    public interface OnEditInputFinishedListener {
        void editInputFinished(String password, String name);
    }

    public InputDialog(Context context, int id, OnEditInputFinishedListener mListener) {
        super(context);
        this.mListener = mListener;
        this.id = id;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_layout);
        //控件
        etPassword = (EditText) findViewById(R.id.et_password);
        etName = (EditText) findViewById(R.id.et_name);
        btnConfrim = (Button) findViewById(R.id.btn_confirm);
        btnCancel = (Button) findViewById(R.id.btn_cancel);
        MyDatabaseHelper dHelper = new MyDatabaseHelper(getContext(), "BookStore.db", null, 1);
        SQLiteDatabase db = dHelper.getWritableDatabase();

        Cursor cursor = db.query("Book", null, "id=?", new String[]{String.valueOf(id)}, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                pages = cursor.getInt(cursor.getColumnIndex("readNum"));
                name = cursor.getString(cursor.getColumnIndex("bookName"));
            } while (cursor.moveToNext());
        }
        cursor.close();

        etPassword.setText(String.valueOf(pages));
        etName.setText(name);
        btnConfrim.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_confirm) {
            //确定
            if (mListener != null) {
                String password = etPassword.getText().toString();
                String name = etName.getText().toString();
                mListener.editInputFinished(password, name);
            }
            dismiss();
        } else {
            //取消
            dismiss();
        }
    }

}