package com.example.retrofittest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

//import com.example.retrofittest.interFace.JsonPlaceHolder;
import com.example.retrofittest.interFace.JsonPlaceHolder;
import com.example.retrofittest.provider.Provider;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    private RecyclerView recycler_main;
    private Adapter adapter;
    private List<Provider> providerList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getPost();

        recycler_main = findViewById(R.id.recycler_main);
        recycler_main.hasFixedSize();
        recycler_main.setLayoutManager(new LinearLayoutManager(this));

    }

    private void getPost(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        JsonPlaceHolder jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);
        String teste = "posts";
        Call<List<Provider>> call = jsonPlaceHolder.getOther(teste);

        call.enqueue(new Callback<List<Provider>>() {
            @Override
            public void onResponse(Call<List<Provider>> call, Response<List<Provider>> response) {
               // List<Provider> providerList = new ArrayList<>();
                if (!response.isSuccessful()){
                    AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
                    alerta.setMessage("Codigo de retorno: "+response.code());
                    alerta.setPositiveButton("ok",null);
                    alerta.show();
                    return;
                }

                providerList = response.body();
                Log.i("logx","Response: "+response.toString());
                adapter = new Adapter(providerList);
                recycler_main.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<Provider>> call, Throwable t) {

            }
        });

    }

}
