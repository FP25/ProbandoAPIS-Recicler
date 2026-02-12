package com.example.recyclerxapi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.recyclerxapi.ApiThings.Animes;
import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHoldar> {
    Context contexto;
    ArrayList<Animes.Anime> animes;

    public RecyclerAdapter(Context context, ArrayList<Animes.Anime> lista) {
        this.contexto = context;
        this.animes = lista;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHoldar onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflador = LayoutInflater.from(contexto);
        View view = inflador.inflate(R.layout.vw_elemento, parent, false);
        return new RecyclerAdapter.ViewHoldar(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoldar holder, int position) {

        holder.tvName.setText(animes.get(position).getAtributos().getAnimeName());
        holder.tvNumCaps.setText("Capitulos: "+animes.get(position).getAtributos().getAnimeCaps());
        holder.tvState.setText("Estado: "+animes.get(position).getAtributos().getAnimeState());
        holder.tvAnID.setText("ID: "+animes.get(position).getAnimeID());

        Glide.with(holder.itemView.getContext()).load(animes.get(position).getAtributos().getPosterImag().getURLImagen()).into(holder.imVw);

        holder.tarjeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle=new Bundle();
                String animeID=animes.get(holder.getAbsoluteAdapterPosition()).getAnimeID();
                bundle.putString("animeId",animeID);

                Intent paguinaDetallada= new Intent(contexto,DetalleSerie.class);
                paguinaDetallada.putExtras(bundle);
                // paguinaDetallada.addFlags(FLAG_ACTIVITY_NEW_TASK);
                contexto.startActivity(paguinaDetallada);

            }
        });


    }

    @Override
    public int getItemCount() {
        return animes.size();
    }

    public class ViewHoldar extends RecyclerView.ViewHolder {

        TextView tvName, tvNumCaps, tvState, tvAnID;
        ImageView imVw;

        CardView tarjeta;

        public ViewHoldar(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.txt_animeName);
            tvNumCaps = itemView.findViewById(R.id.txt_animeCaps);
            tvState = itemView.findViewById(R.id.txt_animeState);
            tvAnID = itemView.findViewById(R.id.animeID);

            imVw = itemView.findViewById(R.id.imVw_animeUrl);
            tarjeta=itemView.findViewById(R.id.trajetaView);

        }
    }
}
