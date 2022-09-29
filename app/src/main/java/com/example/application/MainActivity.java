package com.example.application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView maleTextView, femaleTextView, otherTextView;
    private EditText nameEditText,phoneEditText,emailEditText,addressEditText;
    private final String  maleString  = "Male";
    private final String femaleString = "Female";
    private final String otherString  = "Other";
    private String finalGender = maleString;
    private Integer viewBackground = null;
    private CardView cardView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        maleTextView =findViewById(R.id.male);
        femaleTextView =findViewById(R.id.female);
        otherTextView =findViewById(R.id.other);
        cardView=findViewById(R.id.button);

        nameEditText=findViewById(R.id.nameInput);
        phoneEditText=findViewById(R.id.phoneInput);
        emailEditText=findViewById(R.id.emailInput);
        addressEditText=findViewById(R.id.addressInput);
        progressBar=findViewById(R.id.progressbar);


        maleTextView.setTextColor(getResources().getColor(R.color.white));
        maleTextView.setBackground(AppCompatResources.getDrawable(getApplicationContext(),R.drawable.selectedbutton));
        viewBackground= maleTextView.getId();



        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(nameEditText.getText().toString().isEmpty()&&
                        phoneEditText.getText().toString().isEmpty()&&
                        emailEditText.getText().toString().isEmpty()&&
                        addressEditText.getText().toString().isEmpty())){
                    cardView.setVisibility(View.INVISIBLE);
                    progressBar.setVisibility(View.VISIBLE);
                    postData(nameEditText.getText().toString(),phoneEditText.getText().toString(),
                            emailEditText.getText().toString(),addressEditText.getText().toString(),
                            finalGender);
                }else {
                    Toast.makeText(MainActivity.this, "Fill all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });





        maleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setGender(v.getId());
                setBackground(v.getId());
            }
        });

        femaleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setGender(v.getId());
                setBackground(v.getId());
            }
        });

        otherTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setGender(v.getId());
                setBackground(v.getId());
            }
        });



    }

    @SuppressLint("NonConstantResourceId")
    private void setGender(int id){
        switch (id){
            case R.id.male:finalGender=maleString;
            break;
            case R.id.female:finalGender=femaleString;
            break;
            case R.id.other:finalGender=otherString;
        }
    }

    private void setBackground(Integer view){
        if(viewBackground!=null&& !viewBackground.equals(view)) {
            TextView tempTextViewOld = findViewById(viewBackground);
            TextView tempTextViewNew = findViewById(view);

            tempTextViewOld.setBackground(AppCompatResources.getDrawable(getApplicationContext(),R.drawable.notselectedbutton));
            tempTextViewOld.setTextColor(getResources().getColor(R.color.mustard));
            tempTextViewNew.setBackground(AppCompatResources.getDrawable(getApplicationContext(), R.drawable.selectedbutton));
            tempTextViewNew.setTextColor(getResources().getColor(R.color.white));
            viewBackground=view;

        }
    }




    private void postData(String name, String phone,String email,String address,String gender) {

        Method retrofitAPI = ClientCall.getRetrofit().create(Method.class);

        PostModel modal = new PostModel(2,name,phone,gender,email,address);

        Call<PostModel> call = retrofitAPI.createPost(modal);

        call.enqueue(new Callback<PostModel>() {
            @Override
            public void onResponse(Call<PostModel> call, Response<PostModel> response) {
                cardView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(MainActivity.this, "Data added\n"+
                        "Response Code : " + response.code(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<PostModel> call, Throwable t) {
                cardView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(MainActivity.this, "Error "+ t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void openList(View view) {
        startActivity(new Intent(MainActivity.this,ListActivity.class));
    }
}