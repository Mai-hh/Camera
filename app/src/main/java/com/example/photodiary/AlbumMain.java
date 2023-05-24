package com.example.photodiary;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.photodiary.entity.Image;

import java.util.ArrayList;
import java.util.List;

public class AlbumMain extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private AlbumAdapter mAlbumAdapter;

    private MyAppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.album);
        database = MyApp.getDatabase(this);
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        // 执行数据库查询操作
        new ReadImageTask().execute();
    }

    private class ReadImageTask extends AsyncTask<Void, Void, List<AlbumAdapter.ImageElem>> {
        @Override
        protected List<AlbumAdapter.ImageElem> doInBackground(Void... voids) {
            List<AlbumAdapter.ImageElem> imageList = new ArrayList<>();

            // 执行数据库查询操作
            List<Image> databaseImageList = database.photoDao().getAllEntities();

            // 将数据库中的数据转换为 ImageElem 对象
            for (Image image : databaseImageList) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(image.getImageData(), 0, image.getImageData().length);
                AlbumAdapter.ImageElem imageElem = new AlbumAdapter.ImageElem(image.getTitle(), bitmap);
                imageList.add(imageElem);
            }

            return imageList;
        }

        @Override
        protected void onPostExecute(List<AlbumAdapter.ImageElem> imageList) {
            // 创建适配器并传递数据集
            mAlbumAdapter = new AlbumAdapter(imageList);
            mRecyclerView.setAdapter(mAlbumAdapter);
        }
    }

}
