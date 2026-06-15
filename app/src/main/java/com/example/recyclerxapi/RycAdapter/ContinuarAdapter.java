package com.example.recyclerxapi.RycAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.recyclerxapi.Activities.ContinuarActivity;
import com.example.recyclerxapi.Activities.FinalizadosActivity;
import com.example.recyclerxapi.ApiThings.Animes;
import com.example.recyclerxapi.R;

import java.util.ArrayList;

public class ContinuarAdapter extends RecyclerView.Adapter<ContinuarAdapter.ViewHoldar> {
    Context contexto;
    ArrayList<Animes.Anime> animes;

    public ContinuarAdapter(Context contexto,ArrayList<Animes.Anime>listAnimes){
        this.contexto=contexto;
        this.animes=listAnimes;
    }

    @NonNull
    @Override
    public ContinuarAdapter.ViewHoldar onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflador = LayoutInflater.from(contexto);
        View view = inflador.inflate(R.layout.vw_elemento, parent, false);
        return new ContinuarAdapter.ViewHoldar(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContinuarAdapter.ViewHoldar holder, int position) {

        Animes.Anime anime=animes.get(position);

        holder.animeCard.setOnClickListener(v -> {
            Intent intent = new Intent(contexto, ContinuarActivity.class);
            intent.putExtra("animeId", anime.getAnimeID());
            intent.putExtra("animeName", anime.getAtributos().getAnimeName());
            intent.putExtra("animeState", "Estado: "+animes.get(position).getAtributos().getAnimeState());
            intent.putExtra("animeCaps", "Capitulos: "+animes.get(position).getAtributos().getAnimeCaps());
            intent.putExtra("animeImgUrl", anime.getAtributos().getFondo().getURLImagen());
            contexto.startActivity(intent);
        });


        holder.tvName.setText(animes.get(position).getAtributos().getAnimeName());
        holder.tvNumCaps.setText("Capitulos: "+animes.get(position).getAtributos().getAnimeCaps());
        holder.tvState.setText("Estado: "+animes.get(position).getAtributos().getAnimeState());
        holder.tvAnID.setText("ID: "+animes.get(position).getAnimeID());

        Glide.with(holder.itemView.getContext()).load(animes.get(position).getAtributos().getFondo().getURLImagen()).into(holder.imVw);

    }

    @Override
    public int getItemCount() {
        return animes.size();
    }

    public class ViewHoldar extends RecyclerView.ViewHolder {

        TextView tvName, tvNumCaps, tvState, tvAnID;
        ImageView imVw;
        CardView animeCard;

        public ViewHoldar(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.txt_animeName);
            tvNumCaps = itemView.findViewById(R.id.txt_animeCaps);
            tvState = itemView.findViewById(R.id.txt_animeState);
            tvAnID = itemView.findViewById(R.id.animeID);

            imVw = itemView.findViewById(R.id.imVw_animeUrl);
            animeCard=itemView.findViewById(R.id.animeCardVw);
        }
    }
}
