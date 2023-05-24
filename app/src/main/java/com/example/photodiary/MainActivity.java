package com.example.photodiary;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageView imageView;
    private Button Photo;
    private Button Album;

    private MyAppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Photo = findViewById(R.id.button_photo);
        Album = findViewById(R.id.button_album);
        imageView = findViewById(R.id.image_view1);
        database = MyApp.getDatabase(this);
    }


    @Override
    protected void onStart() {
        super.onStart();


        Photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });

        Album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent albumIntent = new Intent(MainActivity.this, AlbumMain.class);
                startActivity(albumIntent);
            }


        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            // 图片已成功捕获，你可以从返回的Intent对象中获取图片数据
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            jumpToImgEditActivity(imageBitmap);

        }
    }

    private void jumpToImgEditActivity(Bitmap imageBitmap) {
        Intent intent = new Intent(MainActivity.this, PhotoActivity.class);
        intent.putExtra("img", imageBitmap);
        startActivity(intent);
    }

}