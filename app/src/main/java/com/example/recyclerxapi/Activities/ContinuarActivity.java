package com.example.recyclerxapi.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.recyclerxapi.Managers.ContinuarManager;
import com.example.recyclerxapi.Managers.FinalizadosManager;
import com.example.recyclerxapi.R;

import java.util.ArrayList;

public class ContinuarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_continuar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView imvwFondo=findViewById(R.id.imVw_continuar);
        TextView tituloTv=findViewById(R.id.titulo_continuar);
        TextView estadoTv=findViewById(R.id.estado_continuar);
        TextView numCapsTv=findViewById(R.id.numCaps_continuar);
        Spinner editarCaps=findViewById(R.id.lista_continuar);
        ImageButton btnRegreso=findViewById(R.id.btnregreso_continuar);
        Button btnFinalizado=findViewById(R.id.btnFinalizado);

        ArrayList <String>elementos=new ArrayList<>();
        elementos.add("Capitulos");
        elementos.add("Temporadas");

        ArrayAdapter adpSpn=new ArrayAdapter(ContinuarActivity.this, android.R.layout.simple_spinner_dropdown_item,elementos);

        editarCaps.setAdapter(adpSpn);

        editarCaps.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String elemento= (String) editarCaps.getAdapter().getItem(position);

                Toast.makeText(ContinuarActivity.this,"Haz clickeado el elemento "+elemento,Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        String animeId=getIntent().getStringExtra("animeId");
        String animeName=getIntent().getStringExtra("animeName");
        String animeState=getIntent().getStringExtra("animeState");
        String animeCaps=getIntent().getStringExtra("animeCaps");
        String animeImgUrl=getIntent().getStringExtra("animeImgUrl");

        tituloTv.setText(animeName);
        estadoTv.setText(animeState);
        numCapsTv.setText(animeCaps);
        Glide.with(ContinuarActivity.this).load(animeImgUrl).into(imvwFondo);

        btnRegreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        btnFinalizado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FinalizadosManager.addToFinalizados(ContinuarActivity.this,animeId);
                Toast.makeText(ContinuarActivity.this,"Haz acabado de ver la serie",Toast.LENGTH_SHORT).show();
                ContinuarManager.removeToContinuar(ContinuarActivity.this,animeId);
            }
        });


    }
}