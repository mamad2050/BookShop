package com.example.bookshop.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.bookshop.Global.MyPreferencesManager;
import com.example.bookshop.R;

public class AddressActivity extends AppCompatActivity {


    EditText edt_name,edt_phone,edt_address,edt_postalCode, edt_plack,edt_vahed;
    Button btn_go_to_pay;

    MyPreferencesManager myPreferencesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        edt_name = findViewById(R.id.addressActivity_edt_name);
        edt_phone = findViewById(R.id.registerActivity_edt_phone);
        edt_postalCode = findViewById(R.id.addressActivity_edt_postal_code);
        edt_address = findViewById(R.id.addressActivity_edt_address);
        edt_plack = findViewById(R.id.address_activity_edt_plack);
        edt_vahed = findViewById(R.id.address_activity_edt_unit_code);
        btn_go_to_pay = findViewById(R.id.btn_go_to_pay);


        myPreferencesManager = new MyPreferencesManager(this);

//        edt_phone.setText(myPreferencesManager.getUserData().get(MyPreferencesManager.PHONE));

        btn_go_to_pay.setOnClickListener(event -> {

            startActivity(new Intent(AddressActivity.this,SuccessPayActivity.class));

        });


    }
}