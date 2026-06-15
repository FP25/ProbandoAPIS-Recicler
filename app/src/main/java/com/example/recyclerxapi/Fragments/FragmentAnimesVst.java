package com.example.recyclerxapi.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.recyclerxapi.ApiThings.APICall;
import com.example.recyclerxapi.ApiThings.APIinterface;
import com.example.recyclerxapi.ApiThings.Animes;
import com.example.recyclerxapi.R;
import com.example.recyclerxapi.RycAdapter.BusquedaRcyAdapter;
import com.example.recyclerxapi.RycAdapter.FinalizadoRycAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentAnimesVst extends Fragment {
    public FragmentAnimesVst() {
        // Required empty public constructor
    }

    ArrayList<Animes.Anime> anime;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_animes_vst, container, false);

        RecyclerView recyVw=view.findViewById(R.id.recycler_anime_vst);
        APIinterface apiInterface= APICall.getClient().create(APIinterface.class);
        Call<Animes> call=apiInterface.getAnimesById("1555");
        call.enqueue(new Callback<Animes>() {
            @Override
            public void onResponse(Call<Animes> call, Response<Animes> response) {
                Animes anim=response.body();
                anime=anim.getAnimes();
                FinalizadoRycAdapter adapter=new FinalizadoRycAdapter(getContext(),anime);
                recyVw.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<Animes> call, Throwable t) {
                Log.d("Error! llamada fallida.",t.toString());
            }
        });


        return view;
    }
}