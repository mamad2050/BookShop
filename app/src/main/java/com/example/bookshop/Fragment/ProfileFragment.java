package com.example.bookshop.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bookshop.Activity.LoginActivity;
import com.example.bookshop.Global.MyPreferencesManager;
import com.example.bookshop.R;

public class ProfileFragment extends Fragment {

    View view;
    LinearLayout exit_layout;
    TextView txt_username, txt_phone;
MyPreferencesManager myPreferencesManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_profile, container, false);

        txt_username = view.findViewById(R.id.profile_fragment_username);
        txt_phone = view.findViewById(R.id.profile_fragment_phone);

        myPreferencesManager = new MyPreferencesManager(getContext());

        txt_username.setText(myPreferencesManager.getUserData().get(MyPreferencesManager.EMAIL));
        txt_phone.setText(myPreferencesManager.getUserData().get(MyPreferencesManager.PHONE));


        exit_layout = view.findViewById(R.id.profile_exit);

        exit_layout.setOnClickListener(event -> {

            myPreferencesManager.logout();
            startActivity(new Intent(getContext(), LoginActivity.class));

        });


        return view;


    }
}