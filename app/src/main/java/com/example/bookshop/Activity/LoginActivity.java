package com.example.bookshop.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookshop.Api.ApiClient;
import com.example.bookshop.Api.ApiInterface;
import com.example.bookshop.HomeActivity;
import com.example.bookshop.Model.Users;
import com.example.bookshop.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    TextView txt_register;
    Button btn_login;
    ApiInterface request;
    EditText edt_username, edt_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        initialize();


        txt_register.setOnClickListener(e -> startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));


        btn_login.setOnClickListener(event -> {

            String username = edt_username.getText().toString().trim();
            String password = edt_password.getText().toString().trim();

            login(username,password);


        });
    }

    private void initialize() {

        txt_register = findViewById(R.id.login_activity_txt_register);
        btn_login = findViewById(R.id.login_btn);
        request = ApiClient.getRetrofit().create(ApiInterface.class);
        edt_username = findViewById(R.id.loginActivity_edt_user);
        edt_password = findViewById(R.id.loginActivity_edt_password);

    }

    private void login(String username, String password) {


        Call<Users> usersCall = request.loginCall(username,password);
        usersCall.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {

                if (response.isSuccessful() && response.body().isStatus()) {

                   startActivity(new Intent(LoginActivity.this, HomeActivity.class));

                } else {

                    Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {

                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }
}