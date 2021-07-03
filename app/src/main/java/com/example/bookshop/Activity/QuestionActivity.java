package com.example.bookshop.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bookshop.Adapter.QuestionAdapter;
import com.example.bookshop.Global.Constants;
import com.example.bookshop.Model.Question;
import com.example.bookshop.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestionActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    QuestionAdapter questionAdapter;
    List<Question> questions = new ArrayList<>();
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        requestQueue = Volley.newRequestQueue(this);
        recyclerView = findViewById(R.id.activity_question_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        questionAdapter = new QuestionAdapter(this, questions);
        recyclerView.setAdapter(questionAdapter);

        getResponse();


    }

    private void getResponse() {


        StringRequest stringRequest = new StringRequest(Request.Method.GET,Constants.GET_QUESTIONS,response -> {


            Gson gson = new Gson();
            Question[] questionsArray = gson.fromJson(response,Question[].class);
            questions.addAll(Arrays.asList(questionsArray));
            questionAdapter.notifyDataSetChanged();

        },error -> Toast.makeText(this, ""+error.getMessage(), Toast.LENGTH_SHORT).show());

        requestQueue.add(stringRequest);

    }
}