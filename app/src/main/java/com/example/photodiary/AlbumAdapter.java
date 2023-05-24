package com.example.photodiary;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {


    // 用于存放外部传入的数据顺序表
    public List<ImageElem> localDataSet2 = new ArrayList<>();

    // 用于定义顺序表的数据元素的结构


    public static class ImageElem implements Serializable {
        private Bitmap bitmapImg;

        private String imageTitle;

        private String customContent;

        public Bitmap getBitmapImg() {
            return bitmapImg;
        }

        public void setBitmapImg(Bitmap bitmapImg) {
            this.bitmapImg = bitmapImg;
        }

        public String getImageTitle() {
            return imageTitle;
        }

        public void setImageTitle(String imageTitle) {
            this.imageTitle = imageTitle;
        }

        public String getCustomContent() {
            return customContent;
        }

        public void setCustomContent(String customContent) {
            this.customContent = customContent;
        }

        public ImageElem(String name, Bitmap bitmapImg) {
            this.imageTitle = name;
            this.bitmapImg = bitmapImg;
        }
    }

    // 与item_layout相对应的子控件的载体
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final ImageView imageView;
        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            textView = view.findViewById(R.id.text_view);
            imageView = view.findViewById(R.id.image_view);
        }


        public TextView getTextView() {
            return textView;
        }
        public ImageView getImageView() {
            return imageView;
        }
    }

    // CustomAdapter的构造函数
    public AlbumAdapter() {}
    public AlbumAdapter(List<ImageElem> dataSet) {
        localDataSet2 = dataSet;
    }

    // 以下是继承Adapter必须重写的三个函数

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_photo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.getTextView().setText(localDataSet2.get(position).imageTitle);
        holder.getImageView().setImageBitmap(localDataSet2.get(position).bitmapImg);
    }

    @Override
    public int getItemCount() {
        return localDataSet2.size();
    }
}
