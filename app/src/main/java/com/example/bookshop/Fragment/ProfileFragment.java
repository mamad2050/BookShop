package com.example.bookshop.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bookshop.Activity.FavoriteActivity;
import com.example.bookshop.Activity.LoginActivity;
import com.example.bookshop.Activity.OrderActivity;
import com.example.bookshop.Activity.QuestionActivity;
import com.example.bookshop.Global.MyPreferencesManager;
import com.example.bookshop.R;

public class ProfileFragment extends Fragment {

    View view;
    LinearLayout exit_layout, question_layout, wish_layout, orders_layout, address_layout, myInfo_layout;
    TextView txt_username, txt_phone;
    MyPreferencesManager myPreferencesManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_profile, container, false);

        txt_username = view.findViewById(R.id.profile_fragment_username);
        txt_phone = view.findViewById(R.id.profile_fragment_phone);

        myPreferencesManager = new MyPreferencesManager(getContext());

        txt_username.setText(myPreferencesManager.getUserData().get(MyPreferencesManager.USERNAME));
        txt_phone.setText(myPreferencesManager.getUserData().get(MyPreferencesManager.PHONE));


        exit_layout = view.findViewById(R.id.profile_fragment_exit_layout);
        wish_layout = view.findViewById(R.id.profile_fragment_wishes_layout);
        address_layout = view.findViewById(R.id.profile_fragment_address_layout);
        myInfo_layout = view.findViewById(R.id.profile_fragment_myInfo_layout);
        question_layout = view.findViewById(R.id.profile_fragment_question_layout);
        orders_layout = view.findViewById(R.id.profile_fragment_orders_layout);

        exit_layout.setOnClickListener(event -> {

            myPreferencesManager.logout();
            startActivity(new Intent(getContext(), LoginActivity.class));

        });

        question_layout.setOnClickListener(event -> {

            startActivity(new Intent(getContext(), QuestionActivity.class));
        });


        wish_layout.setOnClickListener(e -> startActivity(new Intent(getContext(), FavoriteActivity.class)));

        orders_layout.setOnClickListener(v -> startActivity(new Intent(getContext(), OrderActivity.class)));


        return view;



    }
}