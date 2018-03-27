package com.example.wen.yuedu.activity;

import android.Manifest;
import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;

import android.os.Build;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;


import com.example.wen.yuedu.R;
import com.example.wen.yuedu.utils.SPUtils;
import com.example.wen.yuedu.base.BaseActivity;
import com.example.wen.yuedu.db.MyDatabaseHelper;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.shockwave.pdfium.PdfDocument;
import com.shockwave.pdfium.PdfiumCore;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;


public class AddPDFActivity extends BaseActivity {
    private PDFView pdfView;
    private MyDatabaseHelper dHelper;
    private TextView pageTv, pageTv1;
    private Uri uri;
    private int readPages;
    private int pageCounts;
    private Bitmap cbitmap;
    private int p;
    private String bookName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pdf);
        addToolBar();
        pdfView = findViewById(R.id.pdfView);
        pageTv = (TextView) findViewById(R.id.pageTv);
        pageTv1 = (TextView) findViewById(R.id.pageTv1);
        dHelper = new MyDatabaseHelper(this, "BookStore.db", null, 1);
        if (ContextCompat.checkSelfPermission(AddPDFActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(AddPDFActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            getPath();
        }
    }

    private void getPath() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("*/*");//设置类型，我这里是任意类型，任意后缀的可以这样写。
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {//是否选择，没选择就不会继续
            uri = data.getData();//得到uri，后面就是将uri转化成file的过程。
            final int takeFlags= getIntent().getFlags()&(Intent.FLAG_GRANT_READ_URI_PERMISSION|Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            getContentResolver().takePersistableUriPermission(uri,takeFlags);
            final int myPage = (int) SPUtils.get(AddPDFActivity.this, "page", 0);
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
                            readPages= page;
                            pageTv1.setText(page + "/");
                        }
                    })
                    .scrollHandle(null)
                    .enableAntialiasing(true) // improve rendering a little bit on low-res screens
                    .spacing(0)
                    .onLoad(new OnLoadCompleteListener() {
                        @Override
                        public void loadComplete(int nbPages) {
                            pageCounts=nbPages;
                            pageTv.setText(nbPages + "");
                            pageTv1.setText(myPage +  "/");
                            String BookPath = getFilePath(AddPDFActivity.this, uri);
                            File Bookfile = new File(BookPath);
                            Bitmap bitmap = openPdf(Bookfile);
                            ByteArrayOutputStream os = new ByteArrayOutputStream();
                            if (bitmap != null) {
                                bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
                            }
                            bookName = getFileName(uri);
                            ContentValues values = new ContentValues();
                            SQLiteDatabase db = dHelper.getWritableDatabase();


                            values.put("image", os.toByteArray());
                            values.put("bookName",bookName);
                            values.put("bookNum", pageCounts);
                            values.put("readNum", readPages);
                            values.put("path", uri.toString());
                            db.insert("Book", null, values);
                        }
                    })
                    .load();
            Toast.makeText(AddPDFActivity.this, "导入成功！", Toast.LENGTH_SHORT).show();
        }else{
            finish();
        }


    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //当activity销毁的时候，保存当前的页数，下次打开的时候，直接翻到这个页
        SPUtils.put(AddPDFActivity.this, "page", readPages);
        ContentValues values = values = new ContentValues();
        SQLiteDatabase db = dHelper.getWritableDatabase();
        values.put("readNum",readPages);
        db.update("Book",values,"bookName=?",new String[]{bookName});
    }

    private Bitmap openPdf(File Bookfile) {
        try {
            InputStream is;
            ParcelFileDescriptor fd = ParcelFileDescriptor.open(Bookfile, ParcelFileDescriptor.MODE_READ_ONLY);
            int pageNum = 0;
            PdfiumCore pdfiumCore = new PdfiumCore(AddPDFActivity.this);
            PdfDocument pdfDocument = pdfiumCore.newDocument(fd);
            pdfiumCore.openPage(pdfDocument, pageNum);
            int width = pdfiumCore.getPageWidthPoint(pdfDocument, pageNum);
            int height = pdfiumCore.getPageHeightPoint(pdfDocument, pageNum);
            // ARGB_8888 - best quality, high memory usage, higher possibility of OutOfMemoryError
            // RGB_565 - little worse quality, twice less memory usage
            cbitmap = Bitmap.createBitmap(400, 500,
                    Bitmap.Config.RGB_565);
            pdfiumCore.renderPageBitmap(pdfDocument, cbitmap, pageNum, 0, 0,
                    400, 500);
            //if you need to render annotations and form fields, you can use
            //the same method above adding 'true' as last param
            //iv.setImageBitmap(bitmap);
            //printInfo(pdfiumCore, pdfDocument);
            pdfiumCore.closeDocument(pdfDocument); // important!
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return cbitmap;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getPath();
                } else {
                    Toast.makeText(this, "请允许权限", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }


    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
        if (result == null) {
            result = uri.getLastPathSegment();
        }
        return result;
    }


    public static String getFilePath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{split[1]};

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }


    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }


    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }
}

