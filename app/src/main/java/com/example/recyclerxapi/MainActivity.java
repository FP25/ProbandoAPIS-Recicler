package com.example.recyclerxapi;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerxapi.ApiThings.APICall;
import com.example.recyclerxapi.ApiThings.APIinterface;
import com.example.recyclerxapi.ApiThings.Animes;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    // Paguina para ver el anime https://kitsu.app/anime/cowboy-bebop
    // API https://kitsu.io/api/edge/
    // Doc https://kitsu.docs.apiary.io/#reference/anime

    // hacer una funcion para el filtrado X categorias


    ArrayList<Animes.Anime> animesList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button boton=findViewById(R.id.boton_main);
        EditText ediTxtBusq=findViewById(R.id.main_ET);
        TextView testing=findViewById(R.id.text_test);
        testing.setText("Loading...");

        RecyclerView recyVw=findViewById(R.id.recycler_anime);
        APIinterface apiInterface= APICall.getAnimes().create(APIinterface.class);
        Call<Animes> call=apiInterface.getAnimes();
        call.enqueue(new Callback<Animes>() {
            @Override
            public void onResponse(Call<Animes> call, Response<Animes> response) {
                Animes anim=response.body();
                animesList.addAll(anim.getAnimes());
                RecyclerAdapter adapter=new RecyclerAdapter(MainActivity.this,animesList);
                recyVw.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Animes> call, Throwable t) {
                if (animesList.isEmpty()){
                    testing.setText("Lo sentimos pero hubo un error inesperado.... Paguina caida :(");
                }
                Log.d("Error! llamada fallida.",t.toString());
            }
        });
        testing.setText(" ");

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String busqueda=ediTxtBusq.getText().toString();
                animesList.clear();
                Call<Animes> call=apiInterface.getAnimesByName(busqueda);
                call.enqueue(new Callback<Animes>() {
                    @Override
                    public void onResponse(Call<Animes> call, Response<Animes> response) {
                        Animes anim=response.body();
                        for (Animes.Anime anime:anim.getAnimes()){
                            animesList.add(anime);
                        }
                        RecyclerAdapter adapter=new RecyclerAdapter(MainActivity.this,animesList);
                        recyVw.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Call<Animes> call, Throwable t) {
                        if (animesList.isEmpty()){
                            testing.setText("Lo sentimos pero hubo un error inesperado.... Paguina caida :(");
                        }
                        Log.d("Error! llamada fallida.",t.toString());
                    }
                });
            }
        });
    }
}