package com.example.application;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private RecycleAdapter customAdapter;
    private ArrayList<ModelClass.data> userList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        recyclerView=findViewById(R.id.recycleView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        getUserListFromRestApi();
    }


    private void getUserListFromRestApi() {

        Method methods = ClientCall.getRetrofit().create(Method.class);

        Call<ModelClass> call = methods.getAllData();

        call.enqueue(new Callback<ModelClass>() {
            @Override
            public void onResponse(Call<ModelClass> call, Response<ModelClass> response) {
                userList = new ArrayList<>(response.body().getData());
                customAdapter = new RecycleAdapter(getApplicationContext(), userList);
                recyclerView.setAdapter(customAdapter);


            }

            @Override
            public void onFailure(Call<ModelClass> call, Throwable t) {
                Toast.makeText(ListActivity.this, t.toString(), Toast.LENGTH_SHORT).show();

            }
        });



    }
}