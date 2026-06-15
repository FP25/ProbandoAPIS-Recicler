package com.example.recyclerxapi.Managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FinalizadosManager {
    private static final String PREFS_FINALIZADOS ="FinalizadosPrefs";

    public static void addToFinalizados(Context context, String idAnime){
        List<String> listFinalizados= getFinalizados(context);
        if (!listFinalizados.contains(idAnime)){
            listFinalizados.add(idAnime);
            saveFinalizados(context,listFinalizados);
        }
    }

    public static void removeToFinalizados(Context context, String idAnime){
        List<String> listFinalizados=new ArrayList<>(getFinalizados(context));
        listFinalizados.remove(idAnime);
        saveFinalizados(context,listFinalizados);
    }

    public static void destroy(Context context){
        SharedPreferences prefs = context.getSharedPreferences(PREFS_FINALIZADOS, Context.MODE_PRIVATE);
        prefs.edit().clear().apply();
    }

    public static List<String> getFinalizados(Context context){
        SharedPreferences prefs=context.getSharedPreferences(PREFS_FINALIZADOS,Context.MODE_PRIVATE);
        String idsDFinalizados=prefs.getString(PREFS_FINALIZADOS,"");
        List<String> listFinalizados=new ArrayList<>();
        if (!idsDFinalizados.isEmpty()){
            listFinalizados= new ArrayList<>(Arrays.asList(idsDFinalizados.split(",")));
        }
        return listFinalizados;
    }

    public static boolean isFinished(Context context, String idAnime) {
        return getFinalizados(context).contains(idAnime);
    }

    public static void saveFinalizados(Context context, List<String> idsAnime){
        SharedPreferences prefs=context.getSharedPreferences(PREFS_FINALIZADOS,Context.MODE_PRIVATE);
        prefs.edit().putString(PREFS_FINALIZADOS, TextUtils.join(",", idsAnime)).apply();
    }

    public static void showFinalizados(Context context){
        String mostra="";
        for (String comment: getFinalizados(context)) {
            mostra+=comment+";";
        }
        Log.d("QUE ES LO QUE HAY EN EL SHARED? ","RESULTADO: "+mostra);
    }
}
