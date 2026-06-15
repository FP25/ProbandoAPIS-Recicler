package com.example.recyclerxapi.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.recyclerxapi.Managers.FavoritosManager;
import com.example.recyclerxapi.R;

public class FavoritoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_favorito);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView imvwFondo=findViewById(R.id.imVw_favoritos);
        TextView tituloTv=findViewById(R.id.titulo_favoritos);
        TextView estadoTv=findViewById(R.id.estado_favoritos);
        TextView numCapsTv=findViewById(R.id.numCaps_favoritos);
        ImageButton favoriteBtn=findViewById(R.id.btnFav_favoritos);
        ImageButton savedBtn=findViewById(R.id.btnregreso_favoritos);

        String animeId=getIntent().getStringExtra("animeId");
        String animeName=getIntent().getStringExtra("animeName");
        String animeState=getIntent().getStringExtra("animeState");
        String animeCaps=getIntent().getStringExtra("animeCaps");
        String animeImgUrl=getIntent().getStringExtra("animeImgUrl");

        tituloTv.setText(animeName);
        estadoTv.setText(animeState);
        numCapsTv.setText(animeCaps);
        Glide.with(FavoritoActivity.this).load(animeImgUrl).into(imvwFondo);

        // cambia el icono
        boolean isFav= FavoritosManager.isFavorite(FavoritoActivity.this,animeId);
        if (isFav){
            favoriteBtn.setImageResource(R.drawable.ic_favorito_fill);

        }else{
            favoriteBtn.setImageResource(R.drawable.ic_favoritos);

        }


        favoriteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFav){
                    favoriteBtn.setImageResource(R.drawable.ic_favoritos);
                    FavoritosManager.removeFavorito(FavoritoActivity.this,animeId);
                    Toast.makeText(FavoritoActivity.this,"Se ha retirado de favoritos",Toast.LENGTH_SHORT).show();
                    FavoritosManager.showFavoritos(FavoritoActivity.this);

                }else{
                    favoriteBtn.setImageResource(R.drawable.ic_favorito_fill);
                    Toast.makeText(FavoritoActivity.this,"Se ha añadido a favoritos",Toast.LENGTH_SHORT).show();

                }

            }
        });

        savedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}