package com.example.recyclerxapi;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.recyclerxapi.ApiThings.APICall;
import com.example.recyclerxapi.ApiThings.APIinterface;

public class DetalleSerie extends AppCompatActivity {


    TextView tvTitulo,tvStado;

    ImageView posterAnime,fondoAnime;
    APIinterface apIinterface= APICall.getAnimes().create(APIinterface.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detalle_serie);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        Bundle extras=getIntent().getExtras();
        String animeID=extras.getString("animeId");

        tvTitulo=findViewById(R.id.descriptTitle);
        //tvStado=findViewById(R.id.desc);



    }
}