package com.example.recyclerxapi.Managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListSavedManager {
    private static final String PREFS_LISTSAVED ="ListaSavedPrefs";

    public static void addToList(Context context, String idAnime){
        List<String> listSaved= getList(context);
        if (!listSaved.contains(idAnime)){
            listSaved.add(idAnime);
            saveList(context,listSaved);
        }
    }

    public static void removeToDList(Context context,String idAnime){
        List<String> listSaved=new ArrayList<>(getList(context));
        listSaved.remove(idAnime);
        saveList(context,listSaved);
    }

    public static void destroy(Context context){
        SharedPreferences prefs = context.getSharedPreferences(PREFS_LISTSAVED, Context.MODE_PRIVATE);
        prefs.edit().clear().apply();
    }
    
    public static List<String> getList(Context context){
        SharedPreferences prefs=context.getSharedPreferences(PREFS_LISTSAVED,Context.MODE_PRIVATE);
        String idsSaved=prefs.getString(PREFS_LISTSAVED,"");
        List<String> listSaved=new ArrayList<>();
        if (!idsSaved.isEmpty()){
            listSaved= new ArrayList<>(Arrays.asList(idsSaved.split(",")));
        }
        return listSaved;
    }

    public static boolean isSavedInList(Context context, String idAnime) {
        return getList(context).contains(idAnime);
    }

    public static void saveList(Context context, List<String> idsAnime){
        SharedPreferences prefs=context.getSharedPreferences(PREFS_LISTSAVED,Context.MODE_PRIVATE);
        prefs.edit().putString(PREFS_LISTSAVED, TextUtils.join(",", idsAnime)).apply();
    }

    public static void showList(Context context){
        String mostra="";
        for (String comment: getList(context)) {
            mostra+=comment+";";
        }
        Log.d("QUE ES LO QUE HAY EN EL SHARED? ","RESULTADO: "+mostra);
    }

}
