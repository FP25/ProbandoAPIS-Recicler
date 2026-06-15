package com.example.recyclerxapi.Managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FavoritosManager {

    private static final String PREFS_FAV="AnimesFavsPref";
    private static final String KEY_IDFAVS="AnimesFavsPref";



    public static void addFavorito(Context context,String animeId){
        List<String> listFavs=getFavoritos(context);

        if (!listFavs.contains(animeId)){
            listFavs.add(animeId);
            saveFavoritos(context,listFavs);
        }
    }

    public static void removeFavorito(Context context,String animeId){
        List<String> listFavs=getFavoritos(context);
        listFavs.remove(animeId);

        SharedPreferences prefs = context.getSharedPreferences(PREFS_FAV, Context.MODE_PRIVATE);
        prefs.edit().remove(KEY_IDFAVS + animeId).apply();

        saveFavoritos(context,listFavs);
    }

    public static boolean isFavorite(Context context, String animeId) {
        return getFavoritos(context).contains(animeId);
    }

    public static List<String> getFavoritos(Context context){
        SharedPreferences prefs=context.getSharedPreferences(PREFS_FAV,Context.MODE_PRIVATE);
        String favoritosIds=prefs.getString(PREFS_FAV,"");
        List<String> listFavoritos=new ArrayList<>();
        if (!favoritosIds.isEmpty()){
            listFavoritos= new ArrayList<>(Arrays.asList(favoritosIds.split(",")));
        }
        return listFavoritos;
    }

    public static void saveFavoritos(Context context, List<String> favoritosId){
        SharedPreferences prefs=context.getSharedPreferences(PREFS_FAV,Context.MODE_PRIVATE);
        prefs.edit().putString(KEY_IDFAVS, TextUtils.join(",", favoritosId)).apply();
    }

    public static void showFavoritos(Context context){
        String mostra="";
        for (String anime:getFavoritos(context)) {
            mostra+=anime+",";
        }
        Log.d("QUE ES LO QUE HAY EN EL SHARED? ","RESULTADO: "+mostra);
        SharedPreferences prefs=context.getSharedPreferences(PREFS_FAV,Context.MODE_PRIVATE);
        String esto=prefs.getString(KEY_IDFAVS,"");

        Log.d("QUE ES LO QUE HAY EN EL kry? ","RESULTADOaa: "+esto);

    }

}
