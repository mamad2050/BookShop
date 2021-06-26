package com.example.bookshop.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.bookshop.Global.Key;
import com.example.bookshop.R;

public class BookActivity extends AppCompatActivity {

Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        bundle = getIntent().getExtras();
        Toast.makeText(this, bundle.getString(Key.TITLE), Toast.LENGTH_SHORT).show();
    }
}