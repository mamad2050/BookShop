package com.example.bookshop.DataBase.Local;

import com.example.bookshop.DataBase.DataSource.IFavoriteDataSource;
import com.example.bookshop.DataBase.Model.Favorite;

import java.util.List;

import io.reactivex.Flowable;

public class FavoriteDataSource implements IFavoriteDataSource {


    private FavoriteDao favoriteDao;
    private static FavoriteDataSource instance;

    public FavoriteDataSource(FavoriteDao favoriteDao) {
        this.favoriteDao = favoriteDao;
    }

    public static FavoriteDataSource getInstance(FavoriteDao favoriteDao){

        if (instance==null){
            instance = new FavoriteDataSource(favoriteDao);

        }
        return instance;
    }


    @Override
    public Flowable<List<Favorite>> getListFavoriteItem() {
        return favoriteDao.getListFavoriteItem();
    }

    @Override
    public int isFavorite(int item_id) {
        return favoriteDao.isFavorite(item_id);
    }

    @Override
    public void InsertFavorite(Favorite... favorites) {
        favoriteDao.InsertFavorite(favorites);
    }

    @Override
    public void DeleteFavorite(Favorite favorite) {
        favoriteDao.DeleteFavorite(favorite);
    }
}
