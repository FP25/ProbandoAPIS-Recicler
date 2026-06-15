package com.example.recyclerxapi.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.recyclerxapi.ApiThings.APICall;
import com.example.recyclerxapi.ApiThings.APIinterface;
import com.example.recyclerxapi.ApiThings.Animes;
import com.example.recyclerxapi.R;
import com.example.recyclerxapi.RycAdapter.BusquedaRcyAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentInicio extends Fragment {
    public FragmentInicio() {
        // Required empty public constructor
    }

    ArrayList<Animes.Anime> animesList=new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_inicio, container, false);

        Button boton=view.findViewById(R.id.boton_main);
        EditText ediTxtBusq=view.findViewById(R.id.main_ET);
        TextView testing=view.findViewById(R.id.text_test);

        RecyclerView recyVw=view.findViewById(R.id.recycler_anime);
        APIinterface apiInterface= APICall.getClient().create(APIinterface.class);
        Call<Animes> call=apiInterface.getAnimes();
        call.enqueue(new Callback<Animes>() {
            @Override
            public void onResponse(Call<Animes> call, Response<Animes> response) {
                Animes anim=response.body();
                animesList.addAll(anim.getAnimes());
                BusquedaRcyAdapter adapter=new BusquedaRcyAdapter(getContext(),animesList);
                recyVw.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<Animes> call, Throwable t) {
                Log.d("Error! llamada fallida.",t.toString());
            }
        });

        if (animesList.isEmpty()){
            testing.setText("Lo sentimos pero hubo un error inesperado.... Paguina caida :(");
        }

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
                        BusquedaRcyAdapter adapter=new BusquedaRcyAdapter(getContext(),animesList);
                        recyVw.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Call<Animes> call, Throwable t) {
                        Log.d("Error! llamada fallida.",t.toString());
                    }
                });
            }
        });



        return view;
    }
}