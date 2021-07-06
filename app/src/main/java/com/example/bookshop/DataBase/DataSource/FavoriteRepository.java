package com.example.bookshop.DataBase.DataSource;

import com.example.bookshop.DataBase.Model.Favorite;

import java.util.List;

import io.reactivex.Flowable;

public class FavoriteRepository implements IFavoriteDataSource{
    private IFavoriteDataSource iFavoriteDataSource ;

    private static FavoriteRepository instance;

    public FavoriteRepository(IFavoriteDataSource iFavoriteDataSource) {
        this.iFavoriteDataSource = iFavoriteDataSource;
    }

    public static FavoriteRepository getInstance(IFavoriteDataSource iFavoriteDataSource){

        if (instance == null){
            instance = new FavoriteRepository(iFavoriteDataSource);

        }
        return instance;
    }


    @Override
    public Flowable<List<Favorite>> getListFavoriteItem() {
        return iFavoriteDataSource.getListFavoriteItem();
    }

    @Override
    public int isFavorite(int item_id) {
        return iFavoriteDataSource.isFavorite(item_id);
    }

    @Override
    public void InsertFavorite(Favorite... favorites) {
        iFavoriteDataSource.InsertFavorite(favorites);
    }

    @Override
    public void DeleteFavorite(Favorite favorite) {

        iFavoriteDataSource.DeleteFavorite(favorite);

    }
}
