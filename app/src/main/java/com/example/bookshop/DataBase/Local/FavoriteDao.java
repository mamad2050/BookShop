package com.example.bookshop.DataBase.Local;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.bookshop.DataBase.Model.Favorite;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface FavoriteDao {

    @Query("Select * From Favorite WHERE add_to_favorite = 1 ")
    Flowable<List<Favorite>> getListFavoriteItem();

    @Query("Select Exists (Select 1 From Favorite Where id=:item_id)")
    int isFavorite(int item_id);

    @Insert
    void InsertFavorite(Favorite...favorites);

    @Delete
    void DeleteFavorite(Favorite favorite);


}
