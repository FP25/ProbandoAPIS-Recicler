package com.example.recyclerxapi.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.recyclerxapi.ApiThings.APICall;
import com.example.recyclerxapi.ApiThings.APIinterface;
import com.example.recyclerxapi.ApiThings.Animes;
import com.example.recyclerxapi.Managers.ContinuarManager;
import com.example.recyclerxapi.Managers.ListSavedManager;
import com.example.recyclerxapi.R;
import com.example.recyclerxapi.RycAdapter.ContinuarAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentContinuar extends Fragment {
    public FragmentContinuar() {
        // Required empty public constructor
    }

    private static List<String> listContinuarIds =new ArrayList<>();
    private final ArrayList<Animes.Anime> anime=new ArrayList<>();
    private static ContinuarAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_continuar, container, false);

        TextView noStartPly=view.findViewById(R.id.didntPlay);

        RecyclerView recyVw=view.findViewById(R.id.recycler_anime_ply);
        recyVw.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter=new ContinuarAdapter(getContext(),anime);
        recyVw.setAdapter(adapter);

        APIinterface apiInterface= APICall.getClient().create(APIinterface.class);

        listContinuarIds = ContinuarManager.getContinuarViendo(getContext());

        if (!listContinuarIds.isEmpty()){
            for (int i = 0; i < listContinuarIds.size(); i++){
                llamaAnime(apiInterface, listContinuarIds.get(i));
            }

        }else {
            noStartPly.setText("No has comenzado a ver nada todavia.");
        }


        ContinuarManager.showVistos(getContext());


        return view;
    }
    public void llamaAnime(APIinterface apiInterface,String fav){
        Call<Animes> call=apiInterface.getAnimesById(fav); // aquì la pasaria.
        call.enqueue(new Callback<Animes>() {
            @Override
            public void onResponse(Call<Animes> call, Response<Animes> response) {
                Animes anim=response.body();

                for (Animes.Anime animefav: anim.getAnimes()) {
                    List<Animes.Anime> lista=new ArrayList<>();
                    lista.add(animefav);
                    synchronized (anime) {
                        anime.addAll(lista);
                    }

                }
                adapter.notifyDataSetChanged();

            }
            @Override
            public void onFailure(Call<Animes> call, Throwable t) {
                Log.d("Error! llamada fallida.",t.toString());
            }
        });
    }
}