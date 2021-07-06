package com.example.bookshop.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.bookshop.Adapter.FavoriteAdapter;
import com.example.bookshop.DataBase.DataSource.FavoriteRepository;
import com.example.bookshop.DataBase.Local.FavoriteDataSource;
import com.example.bookshop.DataBase.Local.RoomDataBaseApp;
import com.example.bookshop.DataBase.Model.Favorite;
import com.example.bookshop.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class FavoriteActivity extends AppCompatActivity {

    CompositeDisposable compositeDisposable;
    ///List<Favorite> listFavorite = new ArrayList<>();
    FavoriteAdapter adapter;
    RecyclerView recyclerViewFavorite;
    static RoomDataBaseApp roomDatabaseApp;
    static FavoriteRepository favoriteRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        compositeDisposable = new CompositeDisposable();

        initDatabaseRoom();


        recyclerViewFavorite = findViewById(R.id.favorite_activity_recycler);
        recyclerViewFavorite.setHasFixedSize(true);
        recyclerViewFavorite.setLayoutManager(new LinearLayoutManager(this));



        compositeDisposable.add(favoriteRepository.getListFavoriteItem()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(favorites -> {

                    adapter = new FavoriteAdapter(FavoriteActivity.this,favorites);

                    recyclerViewFavorite.setAdapter(adapter);

                }));


    }


    private void initDatabaseRoom() {

        roomDatabaseApp = RoomDataBaseApp.getInstance(this);
        favoriteRepository = FavoriteRepository.getInstance(FavoriteDataSource.getInstance(roomDatabaseApp.favoriteDao()));

    }
}