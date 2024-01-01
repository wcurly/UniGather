package com.example.unigather.network.Api;

import com.example.unigather.network.Request.LoginRequest;
import com.example.unigather.network.Request.RegisterRequest;
import com.example.unigather.network.Response.LoginResponse;
import com.example.unigather.network.Response.RegisterResponse;
import com.example.unigather.network.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    @GET("users/info")
    Call<List<User>> getUsers();
    @GET("users/{id}")
    Call<User> getUserById(@Path("id")int userId);
    //登录
    @POST("login")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);
    //注册
    @POST("register")
    Call<RegisterResponse> registerUser(@Body RegisterRequest registerRequest);
}
