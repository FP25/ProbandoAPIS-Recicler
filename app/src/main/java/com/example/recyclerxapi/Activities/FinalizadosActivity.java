package com.example.recyclerxapi.Activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.recyclerxapi.Managers.ComentariosManager;
import com.example.recyclerxapi.R;
import com.google.android.material.textfield.TextInputLayout;

public class FinalizadosActivity extends AppCompatActivity {


    static TextView tvComentario;

    static boolean tieneComentario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_finalizados);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        CardView cardView=findViewById(R.id.cdVwComentario);
        tvComentario=findViewById(R.id.cdVwCmmTxt);

        ImageView imvwFondo=findViewById(R.id.imVw_finalizados);
        TextView tituloTv=findViewById(R.id.titulo_finalizados);
        TextView estadoTv=findViewById(R.id.estado_finalizados);
        TextView numCapsTv=findViewById(R.id.numCaps_finalizados);
        ImageButton btnRegreso=findViewById(R.id.btnregreso_finalizados);


        String animeId=getIntent().getStringExtra("animeId");
        String animeName=getIntent().getStringExtra("animeName");
        String animeState=getIntent().getStringExtra("animeState");
        String animeCaps=getIntent().getStringExtra("animeCaps");
        String animeImgUrl=getIntent().getStringExtra("animeImgUrl");

        tituloTv.setText(animeName);
        estadoTv.setText(animeState);
        numCapsTv.setText(animeCaps);
        Glide.with(FinalizadosActivity.this).load(animeImgUrl).into(imvwFondo);

        btnRegreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tieneComentario= ComentariosManager.tieneCommentario(FinalizadosActivity.this,animeId);

        if (tieneComentario){
            tvComentario.setText(ComentariosManager.getComentarioById(FinalizadosActivity.this,animeId));
        }

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(FinalizadosActivity.this);
                LayoutInflater inflador= LayoutInflater.from(FinalizadosActivity.this);
                View dialogCustom = inflador.inflate(R.layout.dialog_custom, null);
                alert.setTitle("Añade tu comentario");
                alert.setCancelable(true);
                TextInputLayout comentario = dialogCustom.findViewById(R.id.dialog_Txt);

                alert.setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tieneComentario= ComentariosManager.tieneCommentario(FinalizadosActivity.this,animeId);
                        if (comentario.getEditText()!=null){
                            if (tieneComentario){
                                ComentariosManager.removeComentario(FinalizadosActivity.this,animeId);
                            }

                            ComentariosManager.addComentario(FinalizadosActivity.this,animeId+"¨"+comentario.getEditText().getText());
                            tvComentario.setText(comentario.getEditText().getText());
                        }
                    }
                });

                alert.setNegativeButton("Shinra tensei", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (comentario.getEditText()!=null){
                            ComentariosManager.destroy(FinalizadosActivity.this);
                        }
                    }
                });

                alert.setView(dialogCustom);
                AlertDialog alertDialog=alert.create();
                alertDialog.show();

            }
        });

    }
}