package com.example.bookshop.DataBase.Local;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.bookshop.DataBase.Model.Favorite;


@Database(entities = {Favorite.class}, version = 1)
public abstract class RoomDataBaseApp extends RoomDatabase {

    public abstract FavoriteDao favoriteDao();

    public static RoomDataBaseApp instance;

    public static RoomDataBaseApp getInstance(Context context) {

        if (instance == null) {

            instance = Room.databaseBuilder(context, RoomDataBaseApp.class, "Shopping Database")
                    .allowMainThreadQueries()
                    .build();

        }

        return instance;

    }

}
