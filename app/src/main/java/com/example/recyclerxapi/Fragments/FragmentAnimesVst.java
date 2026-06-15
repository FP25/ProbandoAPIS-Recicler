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
import com.example.recyclerxapi.Managers.FinalizadosManager;
import com.example.recyclerxapi.R;
import com.example.recyclerxapi.RycAdapter.FinalizadoRycAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentAnimesVst extends Fragment {
    public FragmentAnimesVst() {
        // Required empty public constructor
    }

    private static List<String> listFinalizados =new ArrayList<>();
    private final ArrayList<Animes.Anime> anime=new ArrayList<>();
    private static FinalizadoRycAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_animes_vst, container, false);

        TextView noStartPly=view.findViewById(R.id.noVistos);

        RecyclerView recyVw=view.findViewById(R.id.recycler_anime_vst);
        recyVw.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter=new FinalizadoRycAdapter(getContext(),anime);
        recyVw.setAdapter(adapter);

        APIinterface apiInterface= APICall.getClient().create(APIinterface.class);

        listFinalizados = FinalizadosManager.getFinalizados(getContext());

        if (!listFinalizados.isEmpty()){
            for (int i = 0; i < listFinalizados.size(); i++){
                llamaAnime(apiInterface, listFinalizados.get(i));
            }

        }else {
            noStartPly.setText("No has tenido actividad todavia.");
        }

        FinalizadosManager.showFinalizados(getContext());


        return view;
    }

    public void llamaAnime(APIinterface apiInterface,String idAnimeVisto){
        Call<Animes> call=apiInterface.getAnimesById(idAnimeVisto); // aquì la pasaria.
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