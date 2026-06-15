package com.example.recyclerxapi.Activities;

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
import com.example.recyclerxapi.Managers.ListSavedManager;
import com.example.recyclerxapi.R;

public class ListaSavedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_saved);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView imvwFondo=findViewById(R.id.imVw_lista);
        TextView tituloTv=findViewById(R.id.titulo_lista);
        TextView estadoTv=findViewById(R.id.estado_lista);
        TextView numCapsTv=findViewById(R.id.numCaps_lista);
        ImageButton btnFavorite=findViewById(R.id.btnFav_lista);
        ImageButton btnContinuar=findViewById(R.id.btnPlay_lista);
        ImageButton btnRegreso=findViewById(R.id.btnregreso_lista);

        String animeId=getIntent().getStringExtra("animeId");
        String animeName=getIntent().getStringExtra("animeName");
        String animeState=getIntent().getStringExtra("animeState");
        String animeCaps=getIntent().getStringExtra("animeCaps");
        String animeImgUrl=getIntent().getStringExtra("animeImgUrl");

        tituloTv.setText(animeName);
        estadoTv.setText(animeState);
        numCapsTv.setText(animeCaps);
        Glide.with(ListaSavedActivity.this).load(animeImgUrl).into(imvwFondo);

        // logica de regreso
        btnRegreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isWatching= ContinuarManager.isWatching(ListaSavedActivity.this,animeId);
                if (!isWatching){
                    btnContinuar.setImageResource(R.drawable.ic_dejar_d_ver);
                    Toast.makeText(ListaSavedActivity.this,"Haz comenzando a ver la serie",Toast.LENGTH_SHORT).show();
                    ContinuarManager.addToPlay(ListaSavedActivity.this,animeId);
                    ListSavedManager.removeToDList(ListaSavedActivity.this,animeId);
                }else{
                    btnContinuar.setImageResource(R.drawable.ic_comenzar);
                    Toast.makeText(ListaSavedActivity.this,"Haz dejado de ver la serie",Toast.LENGTH_SHORT).show();
                    ContinuarManager.removeToContinuar(ListaSavedActivity.this,animeId);
                    ListSavedManager.addToList(ListaSavedActivity.this,animeId);
                }
            }
        });


    }
}