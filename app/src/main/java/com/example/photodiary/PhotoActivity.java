package com.example.photodiary;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;


import com.example.photodiary.entity.Image;
import com.example.photodiary.utils.BitmapUtils;

public class PhotoActivity extends AppCompatActivity {

    private Bitmap bitmapImg;
    private ImageView imageView;
    private EditText editTitle;
    private EditText editText;
    private MyAppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        imageView = findViewById(R.id.photo);
        editTitle = findViewById(R.id.edit_title);
        editText = findViewById(R.id.edit_text);

        bitmapImg = getIntent().getParcelableExtra("img");
        if (bitmapImg == null) {
            finish();
        }

        database = MyApp.getDatabase(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        imageView.setImageBitmap(bitmapImg);
    }


    @Override
    protected void onPause() {
        super.onPause();

        String title = editTitle.getText().toString();
        String content = editText.getText().toString();

        // 创建 Image 对象并设置相关属性
        Image image = new Image();
        image.setImageData(BitmapUtils.convertBitmapToByteArray(bitmapImg));
        image.setTitle(title);
        image.setCustomContent(content);

        // 使用异步任务插入 Image 对象到数据库
        new InsertImageTask().execute(image);
    }

    private class InsertImageTask extends AsyncTask<Image, Void, Void> {
        @Override
        protected Void doInBackground(Image... images) {
            // 插入 Image 对象到数据库
            PhotoDao imageDao = database.photoDao();
            imageDao.insert(images[0]);
            return null;
        }
    }
}