package com.example.retrofittest.interFace;

import com.example.retrofittest.provider.Provider;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JsonPlaceHolder {

    @GET("posts")
    Call<List<Provider>> getPost();

    @GET("{posts}")
    Call<List<Provider>> getOther(@Path("posts")String teste);

}
