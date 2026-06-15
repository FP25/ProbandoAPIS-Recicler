package com.example.recyclerxapi.Activities;

import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.util.Log;
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
import com.example.recyclerxapi.Managers.ContinuarManager;
import com.example.recyclerxapi.Managers.FavoritosManager;
import com.example.recyclerxapi.Managers.ListSavedManager;
import com.example.recyclerxapi.R;

public class InicioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_inicio);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView imvwFondo=findViewById(R.id.imVw_busqueda);
        TextView tituloTv=findViewById(R.id.titulo_busqueda);
        TextView estadoTv=findViewById(R.id.estado_busqueda);
        TextView numCapsTv=findViewById(R.id.numCaps_busqueda);
        ImageButton btnFavorite=findViewById(R.id.btnFav_busqueda);
        ImageButton btnSaved=findViewById(R.id.btnSave_busqueda);
        ImageButton btnContinuar=findViewById(R.id.btnPlay_busqueda);
        ImageButton btnRegreso=findViewById(R.id.btnregreso_inicio);

        String animeId=getIntent().getStringExtra("animeId");
        String animeName=getIntent().getStringExtra("animeName");
        String animeState=getIntent().getStringExtra("animeState");
        String animeCaps=getIntent().getStringExtra("animeCaps");
        String animeImgUrl=getIntent().getStringExtra("animeImgUrl");

        tituloTv.setText(animeName);
        estadoTv.setText(animeState);
        numCapsTv.setText(animeCaps);
        Glide.with(InicioActivity.this).load(animeImgUrl).into(imvwFondo);

        btnRegreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isFav=FavoritosManager.isFavorite(InicioActivity.this,animeId);
                if (!isFav){
                    btnFavorite.setImageResource(R.drawable.ic_favorito_fill);
                    Toast.makeText(InicioActivity.this,"Añadido en favoritos",Toast.LENGTH_SHORT).show();
                    FavoritosManager.addFavorito(InicioActivity.this,animeId);
                }else{
                    btnFavorite.setImageResource(R.drawable.ic_favoritos);
                    Toast.makeText(InicioActivity.this,"removido de favoritos",Toast.LENGTH_SHORT).show();
                    FavoritosManager.removeFavorito(InicioActivity.this,animeId);
                }


            }
        });

        btnSaved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isSaved= ListSavedManager.isSavedInList(InicioActivity.this,animeId);
                if (!isSaved){
                    btnSaved.setImageResource(R.drawable.ic_ispendiente);
                    Toast.makeText(InicioActivity.this,"Añadido a la lista de pendientes",Toast.LENGTH_SHORT).show();
                    ListSavedManager.addToList(InicioActivity.this,animeId);
                }else{
                    btnSaved.setImageResource(R.drawable.ic_pendientes);
                    Toast.makeText(InicioActivity.this,"removido de la lista de pendientes",Toast.LENGTH_SHORT).show();
                    ListSavedManager.removeToDList(InicioActivity.this,animeId);
                }
            }
        });

        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isWatching= ContinuarManager.isWatching(InicioActivity.this,animeId);
                if (!isWatching){
                    btnContinuar.setImageResource(R.drawable.ic_dejar_d_ver);
                    Toast.makeText(InicioActivity.this,"Haz comenzando a ver la serie",Toast.LENGTH_SHORT).show();
                    ContinuarManager.addToPlay(InicioActivity.this,animeId);
                }else{
                    btnContinuar.setImageResource(R.drawable.ic_comenzar);
                    Toast.makeText(InicioActivity.this,"Haz dejado de ver la serie",Toast.LENGTH_SHORT).show();
                    ContinuarManager.removeToContinuar(InicioActivity.this,animeId);
                }
            }
        });


    }
}