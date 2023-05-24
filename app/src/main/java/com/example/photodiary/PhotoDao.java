package com.example.photodiary;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.photodiary.entity.Image;

import java.util.List;

@Dao
public interface PhotoDao {
    @Insert
    void insert(Image image);

    @Query("SELECT * FROM table_photo")
    List<Image> getAllEntities();
}
