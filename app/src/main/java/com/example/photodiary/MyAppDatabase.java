package com.example.photodiary;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.photodiary.entity.Image;


@Database(entities = {Image.class}, version = 1, exportSchema = false)
public abstract class MyAppDatabase extends RoomDatabase {
    public abstract PhotoDao photoDao();

}



