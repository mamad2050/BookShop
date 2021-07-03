package com.example.bookshop.Global;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.bookshop.Model.UserData;

import java.util.HashMap;

public class MyPreferencesManager {

    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public static final String IS_LOGGED_IN = "IS_LOGGED_IN";
    public static final String ID = "ID";
    public static final String PHONE = "PHONE";
    public static final String EMAIL = "EMAIL";
    public static final String USERNAME = "USERNAME";


    public MyPreferencesManager(Context context) {
        this.context = context;

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();

    }

    public void saveUserData(UserData userData) {

        editor.putBoolean(IS_LOGGED_IN, true);
        editor.putString(EMAIL, userData.getEmail());
        editor.putString(ID, userData.getId());
        editor.putString(PHONE, userData.getPhone());
        editor.putString(USERNAME, userData.getUsername());
        editor.commit();
    }


    public void logout() {

        editor.clear();
        editor.commit();

    }

    public HashMap<String, String> getUserData() {

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(ID, sharedPreferences.getString(ID, null));
        hashMap.put(PHONE, sharedPreferences.getString(PHONE, null));
        hashMap.put(USERNAME, sharedPreferences.getString(USERNAME, null));
        hashMap.put(EMAIL, sharedPreferences.getString(EMAIL, null));
        return hashMap;

    }

    public boolean isLoggedIn() {


        return sharedPreferences.getBoolean(IS_LOGGED_IN,false);
    }


}
