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
import com.example.recyclerxapi.Managers.FavoritosManager;
import com.example.recyclerxapi.R;
import com.example.recyclerxapi.RycAdapter.FavoritosAdapter;
import com.example.recyclerxapi.RycAdapter.FinalizadoRycAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentFavoritos extends Fragment {
    public FragmentFavoritos() {
        // Required empty public constructor
    }

    private static List<String> favoritosId=new ArrayList<>();
    private final ArrayList<Animes.Anime> anime=new ArrayList<>();
    private static FavoritosAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_favoritos, container, false);

        TextView noFavs=view.findViewById(R.id.noHatFavs);

        RecyclerView recyVw=view.findViewById(R.id.recycler_anime_favs);
        recyVw.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter=new FavoritosAdapter(getContext(),anime);
        recyVw.setAdapter(adapter);

        APIinterface apiInterface= APICall.getClient().create(APIinterface.class);

        favoritosId=FavoritosManager.getFavoritos(getContext()); // iterar todas las id de la lista para mandarlas a llamar.

        if (!favoritosId.isEmpty()){
            for (int i = 0; i < favoritosId.size(); i++){
                llamaAnime(apiInterface,favoritosId.get(i));
            }


        }else {
            noFavs.setText("Actualmente no tienes favoritos.");
        }


        FavoritosManager.showFavoritos(getContext());

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
                Log.d("LISTA ESTARA SINCRO ?","LITA: "+anime);

            }
            @Override
            public void onFailure(Call<Animes> call, Throwable t) {
                Log.d("Error! llamada fallida.",t.toString());
            }
        });
    }

}