package com.example.photodiary;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

public class MyApp extends Application {
    private static MyAppDatabase database;

    public static MyAppDatabase getDatabase(Context context) {
        if (database == null) {
            database = Room.databaseBuilder(context.getApplicationContext(),
                            MyAppDatabase.class, "photos")
                    .build();
        }
        return database;
    }
}

