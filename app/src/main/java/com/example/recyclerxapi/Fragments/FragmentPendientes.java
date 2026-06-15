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
import com.example.recyclerxapi.Managers.ListSavedManager;
import com.example.recyclerxapi.R;
import com.example.recyclerxapi.RycAdapter.FavoritosAdapter;
import com.example.recyclerxapi.RycAdapter.ListSavedAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentPendientes extends Fragment {

    public FragmentPendientes() {
        // Required empty public constructor
    }

    private static List<String> listSavedId =new ArrayList<>();
    private final ArrayList<Animes.Anime> anime=new ArrayList<>();
    private static ListSavedAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_pendientes, container, false);

        TextView noList=view.findViewById(R.id.noHaveList);

        RecyclerView recyVw=view.findViewById(R.id.recycler_anime_list);
        recyVw.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter=new ListSavedAdapter(getContext(),anime);
        recyVw.setAdapter(adapter);

        APIinterface apiInterface= APICall.getClient().create(APIinterface.class);

        listSavedId = ListSavedManager.getList(getContext());

        if (!listSavedId.isEmpty()){
            for (int i = 0; i < listSavedId.size(); i++){
                llamaAnime(apiInterface, listSavedId.get(i));
            }


        }else {
            noList.setText("Actualmente no tienes ningun elemento guardado.");
        }


        ListSavedManager.showList(getContext());


        return view;
    }
    public void llamaAnime(APIinterface apiInterface,String pendings){
        Call<Animes> call=apiInterface.getAnimesById(pendings); // aquì la pasaria.
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