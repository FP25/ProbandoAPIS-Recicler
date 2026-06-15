package com.example.recyclerxapi.Managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ContinuarManager {
    private static final String PREFS_CONTINUAR ="ContinuarViendoPrefs";

    public static void addToPlay(Context context, String idAnime){
        List<String> listContinuar= getContinuarViendo(context); //continuar es continuar viendo
        if (!listContinuar.contains(idAnime)){
            listContinuar.add(idAnime);
            saveVistos(context,listContinuar);
        }
    }

    public static void removeToContinuar(Context context, String idAnime){
        List<String> listContinuar=new ArrayList<>(getContinuarViendo(context));
        listContinuar.remove(idAnime);
        saveVistos(context,listContinuar);
    }

    public static void destroy(Context context){
        SharedPreferences prefs = context.getSharedPreferences(PREFS_CONTINUAR, Context.MODE_PRIVATE);
        prefs.edit().clear().apply();
    }

    public static List<String> getContinuarViendo(Context context){
        SharedPreferences prefs=context.getSharedPreferences(PREFS_CONTINUAR,Context.MODE_PRIVATE);
        String idsContinuarViendo=prefs.getString(PREFS_CONTINUAR,"");
        List<String> listContinuar=new ArrayList<>();
        if (!idsContinuarViendo.isEmpty()){
            listContinuar= new ArrayList<>(Arrays.asList(idsContinuarViendo.split(",")));
        }
        return listContinuar;
    }

    public static boolean isWatching(Context context, String idAnime) {
        return getContinuarViendo(context).contains(idAnime);
    }

    public static void saveVistos(Context context, List<String> idsAnime){
        SharedPreferences prefs=context.getSharedPreferences(PREFS_CONTINUAR,Context.MODE_PRIVATE);
        prefs.edit().putString(PREFS_CONTINUAR, TextUtils.join(",", idsAnime)).apply();
    }

    public static void showVistos(Context context){
        String mostra="";
        for (String comment: getContinuarViendo(context)) {
            mostra+=comment+";";
        }
        Log.d("QUE ES LO QUE HAY EN EL SHARED? ","RESULTADO: "+mostra);
    }
}
