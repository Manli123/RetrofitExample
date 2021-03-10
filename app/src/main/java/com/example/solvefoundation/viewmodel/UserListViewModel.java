package com.example.solvefoundation.viewmodel;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.solvefoundation.MainActivity;
import com.example.solvefoundation.UserDetails;
import com.example.solvefoundation.model.User;
import com.example.solvefoundation.network.ApiInterface;
import com.example.solvefoundation.network.RetroInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserListViewModel extends ViewModel {
    private MutableLiveData<List<User>> userList;

    public UserListViewModel(){
        userList = new MutableLiveData<>();
    }



}
