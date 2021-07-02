package com.example.bookshop.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.bookshop.Activity.LoginActivity;
import com.example.bookshop.R;

public class ProfileFragment extends Fragment {

    View view;
    LinearLayout exit_layout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {





        view = inflater.inflate(R.layout.fragment_profile, container, false);

        exit_layout = view.findViewById(R.id.profile_exit);
        exit_layout.setOnClickListener(event -> startActivity(new Intent(getContext(), LoginActivity.class)));

        return view;


    }
}