package com.example.solvefoundation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.solvefoundation.model.User;
import com.example.solvefoundation.network.ApiInterface;
import com.example.solvefoundation.network.RetroInstance;
import com.example.solvefoundation.viewmodel.UserListViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity  {


    private UserListViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView=findViewById(R.id.listView);
        viewModel = ViewModelProviders.of(this).get(UserListViewModel.class);

       // Retrofit retrofit=new Retrofit.Builder().
              //  baseUrl("https://jsonplaceholder.typicode.com/")
               // .addConverterFactory(GsonConverterFactory.create())
               // .build();

        //ApiInterface myapi=retrofit.create(ApiInterface.class);
       // Call<List<User>> usersCall=myapi.getUsers();
        //Call<List<Company>> companycall=myapi.getCompany();




        ApiInterface apiService = RetroInstance.getRetroClient().create(ApiInterface.class);
        Call<List<User>> usersCall = apiService.getUsers();




        usersCall.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> userList=response.body();



                String[] username=new  String[userList.size()];
                String[] userEmail=new  String[userList.size()];
                String[] userCompany=new  String[userList.size()];
                String[] userPhone=new  String[userList.size()];
                String[] userWeb=new  String[userList.size()];

                for (int i=0;i<userList.size();i++)
                {
                    username[i]=userList.get(i).getName();
                    userEmail[i]=userList.get(i).getEmail();
                    userCompany[i]= String.valueOf(userList.get(i).getCompany().getName());
                    userPhone[i]= userList.get(i).getPhone();
                    userWeb[i]=userList.get(i).getWebsite();
                }
                listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,username));



                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        //String address=userList.get((int) id).getAddress().getCity().toString();
                        Intent intent=new Intent(MainActivity.this, UserDetails.class);
                        intent.putExtra("name",username[position]);
                        intent.putExtra("Email",userEmail[position]);
                        intent.putExtra("Company",userCompany[position]);
                        intent.putExtra("phone",userPhone[position]);
                        intent.putExtra("website",userWeb[position]);
                        startActivity(intent);
                    }
                });


            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG);

            }
        });
    }
}