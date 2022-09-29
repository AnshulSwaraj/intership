package com.example.application;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Method {

    @GET("filmdial_app/public/api/auth/get_department")
    Call<ModelClass> getAllData();

    @POST("filmdial_app/public/api/auth/register")
    Call<PostModel> createPost(@Body PostModel dataModal);

}
