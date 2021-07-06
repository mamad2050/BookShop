package com.example.bookshop.DataBase.DataSource;

import com.example.bookshop.DataBase.Model.Favorite;

import java.util.List;

import io.reactivex.Flowable;

public interface IFavoriteDataSource {

    Flowable<List<Favorite>> getListFavoriteItem();

    int isFavorite(int item_id);

    void InsertFavorite(Favorite... favorites);

    void DeleteFavorite(Favorite favorite);

}
