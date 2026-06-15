package com.example.recyclerxapi.Activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.recyclerxapi.ApiThings.Animes;
import com.example.recyclerxapi.Fragments.FragmentAnimesVst;
import com.example.recyclerxapi.Fragments.FragmentContinuar;
import com.example.recyclerxapi.Fragments.FragmentFavoritos;
import com.example.recyclerxapi.Fragments.FragmentInicio;
import com.example.recyclerxapi.Fragments.FragmentMangasRds;
import com.example.recyclerxapi.Fragments.FragmentPendientes;
import com.example.recyclerxapi.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    // Paguina para ver el anime https://kitsu.app/anime/cowboy-bebop
    // API https://kitsu.io/api/edge/
    // Doc https://kitsu.docs.apiary.io/#reference/anime

    // hacer una funcion para el filtrado X categorias

    TextView titulo;

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        titulo=findViewById(R.id.txt_toolbar);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        drawerLayout=findViewById(R.id.main);

        NavigationView navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.app_name,R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor_fragments,new FragmentInicio()).commit();
            navigationView.setCheckedItem(R.id.nav_busqueda);
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.nav_busqueda) {
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor_fragments,new FragmentInicio()).commit();
            titulo.setText("Bienvenido!");
        }else if (menuItem.getItemId() == R.id.nav_favoritos) {
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor_fragments, new FragmentFavoritos()).commit();
            titulo.setText("Mis favoritos");
            titulo.setTextSize(45);
        }else if (menuItem.getItemId() == R.id.nav_continuar) {
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor_fragments, new FragmentContinuar()).commit();
            titulo.setText("Continuar viendo");
            titulo.setTextSize(40);
        }else if (menuItem.getItemId() == R.id.nav_list) {
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor_fragments,new FragmentPendientes()).commit();
            titulo.setText("Mis lista de pendientes");
            titulo.setTextSize(33);
        } else if (menuItem.getItemId() == R.id.nav_animesVistos) {
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor_fragments,new FragmentAnimesVst()).commit();
            titulo.setText("Mis animes vistos");
            titulo.setTextSize(40);
        }else if (menuItem.getItemId() == R.id.nav_mangasLeidos) {
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor_fragments,new FragmentMangasRds()).commit();
            titulo.setText("Mis mangas leidos");
            titulo.setTextSize(40);
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}