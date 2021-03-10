package com.example.solvefoundation.network;

import com.example.solvefoundation.model.Company;
import com.example.solvefoundation.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    String BASE_URL = "https://jsonplaceholder.typicode.com/";
    @GET("users")
    Call<List<User>> getUsers();

    @GET("company")
    Call<List<Company>> getCompany();


}
