package com.example.bookshop.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.request.RequestCoordinator;
import com.example.bookshop.Api.ApiClient;
import com.example.bookshop.Api.ApiInterface;
import com.example.bookshop.Model.User;
import com.example.bookshop.R;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegisterActivity extends AppCompatActivity {


    EditText usernameEditText, mailEditText, passwordEditText, phoneEditText;
    Button registerBTN;
    ApiInterface request;
    String baseUrl = "http://scusad-bookshoponline.fandogh.cloud/api/rest-auth/";

    private static final String TAG = "RegisterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initialize();
        register();
    }

    private void initialize() {

        usernameEditText = findViewById(R.id.registerActivity_edt_user);
        mailEditText = findViewById(R.id.registerActivity_edt_mail);
        passwordEditText = findViewById(R.id.registerActivity_edt_password);
        phoneEditText = findViewById(R.id.registerActivity_edt_phone);
        registerBTN = findViewById(R.id.registerActivity_btn);
        request = ApiClient.getRetrofit(baseUrl).create(ApiInterface.class);

    }

    private void register() {


        registerBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = usernameEditText.getText().toString().trim();
                String email = mailEditText.getText().toString().trim();
                String password1 = passwordEditText.getText().toString().trim();
                String password2 = passwordEditText.getText().toString().trim();
                String phone = phoneEditText.getText().toString().trim();

                User user = new User(username, email, password1, password2);

                Call<User>userCall = request.register(user);
                String json = new Gson().toJson(user,User.class);
                userCall.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {

                        if (!response.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "Not Successful", Toast.LENGTH_SHORT).show();
                            return;
                        }else{

                            Toast.makeText(RegisterActivity.this, "ok", Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

    }
}