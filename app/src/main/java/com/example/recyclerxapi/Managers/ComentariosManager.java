package com.example.recyclerxapi.Managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ComentariosManager {
    private static final String PREFS_COMM="ComentariosPrefs";

    public static void addComentario(Context context, String idYCommt){
        List<String> listCommt=getComentarios(context);
        if (!listCommt.contains(idYCommt)){
            listCommt.add(idYCommt);
            saveComentarios(context,listCommt);
        }
    }

    public static void removeComentario(Context context,String idcommt){
        List<String> listCommts=new ArrayList<>(getComentarios(context));
        for (String idWithCommt:listCommts) {
            if (idWithCommt.contains(idcommt)){
                listCommts.remove(idWithCommt);
            }
        }
        saveComentarios(context,listCommts);
    }

    public static void destroy(Context context){
        SharedPreferences prefs = context.getSharedPreferences(PREFS_COMM, Context.MODE_PRIVATE);
        prefs.edit().clear().apply();
    }

    public static List<String> getComentarios(Context context){
        SharedPreferences prefs=context.getSharedPreferences(PREFS_COMM,Context.MODE_PRIVATE);
        String idsYcommts=prefs.getString(PREFS_COMM,"");
        List<String> listCommts=new ArrayList<>();
        if (!idsYcommts.isEmpty()){
            listCommts= new ArrayList<>(Arrays.asList(idsYcommts.split(";")));
        }
        return listCommts;
    }

    public static String getComentarioById(Context context,String idAnime){
        SharedPreferences prefs=context.getSharedPreferences(PREFS_COMM,Context.MODE_PRIVATE);
        String idsYcommts=prefs.getString(PREFS_COMM,"");
        String comentario="";
        List<String> listCommts=new ArrayList<>();
        if (!idsYcommts.isEmpty()){
            listCommts= new ArrayList<>(Arrays.asList(idsYcommts.split(";")));
            for (String idWithCommt:listCommts) {
                if (idWithCommt.contains(idAnime)){
                    comentario=idWithCommt.split("¨")[1]; // buscaba un simbolo que fuera poco usado.
                }
            }
        }
        return comentario;
    }

    public static boolean tieneCommentario(Context context, String idCommt) {
        List<String> listCommts=new ArrayList<>(getComentarios(context));
            for (String idWithCommt:listCommts) {
                if (idWithCommt.contains(idCommt)){
                    return true;
                }
            }
            return false;
    }

    public static void saveComentarios(Context context, List<String> idYCommt){
        SharedPreferences prefs=context.getSharedPreferences(PREFS_COMM,Context.MODE_PRIVATE);
        prefs.edit().putString(PREFS_COMM, TextUtils.join(";", idYCommt)).apply();
    }

    public static void showComentarios(Context context){
        String mostra="";
        for (String comment:getComentarios(context)) {
            mostra+=comment+";";
        }
        Log.d("QUE ES LO QUE HAY EN EL SHARED? ","RESULTADO: "+mostra);
    }

}
