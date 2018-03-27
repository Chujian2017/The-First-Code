package com.example.wen.yuedu.activity;


import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wen.yuedu.R;
import com.example.wen.yuedu.db.MyDatabaseHelper;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;


public class ReadPDFActivity extends AppCompatActivity {
    private PDFView pdfView;
    private MyDatabaseHelper dHelper;
    private TextView pageTv, pageTv1;
    private int readPages;
    private String bookId;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pdf);
        pageTv = (TextView) findViewById(R.id.pageTv);
        pageTv1 = (TextView) findViewById(R.id.pageTv1);
        pdfView = findViewById(R.id.pdfView);
        Intent intent=getIntent();
        bookId=intent.getStringExtra("bookId");
        dHelper = new MyDatabaseHelper(this, "BookStore.db", null, 1);
        if (ContextCompat.checkSelfPermission(ReadPDFActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ReadPDFActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            openPDF();
        }
    }

    private void openPDF() {

        dHelper=new MyDatabaseHelper(this,"BookStore.db",null,1);
        SQLiteDatabase db = dHelper.getWritableDatabase();
        String[]book={bookId};
        Cursor cursor=db.query("Book",null,"id=?",book,null,null,null);

        if(cursor.moveToFirst()){
            do{

                String path=cursor.getString(cursor.getColumnIndex("path"));
                Uri uri=Uri.parse(path);
                readPages=cursor.getInt(cursor.getColumnIndex("readNum"));
                final int myPage=readPages;
                pdfView.recycle();
                pdfView.fromUri(uri)
                        .enableSwipe(true) // allows to block changing pages using swipe
                        .swipeHorizontal(false)
                        .enableDoubletap(true)
                        .defaultPage(myPage)
                        .enableAnnotationRendering(false) // render annotations (such as comments, colors or forms)
                        .password(null)
                        .onPageChange(new OnPageChangeListener() {

                            @Override
                            public void onPageChanged(int page, int pageCount) {
                                readPages = page;
                                pageTv1.setText(page + "/");
                            }
                        })
                        .scrollHandle(null)
                        .enableAntialiasing(true) // improve rendering a little bit on low-res screens
                        .spacing(0)
                        .onLoad(new OnLoadCompleteListener() {
                            @Override
                            public void loadComplete(int nbPages) {
                                pageTv.setText(nbPages + "");
                                pageTv1.setText(myPage + "/");
                            }
                        })
                        .load();
            }while(cursor.moveToNext());
        }cursor.close();
    }

    protected void onDestroy() {
        super.onDestroy();
        //当activity销毁的时候，保存当前的页数，下次打开的时候，直接翻到这个页
        ContentValues values = values = new ContentValues();
        SQLiteDatabase db = dHelper.getWritableDatabase();
        values.put("readNum", readPages);
        db.update("Book", values, "id=?", new String[]{bookId});
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openPDF();
                } else {
                    Toast.makeText(this, "请允许权限", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }
}





