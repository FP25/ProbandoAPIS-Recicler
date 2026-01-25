package com.example.recyclerxapi.ApiThings;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Animes {

    @SerializedName("data")
    private ArrayList<Anime> animes = new ArrayList<>();

    public ArrayList<Anime> getAnimes() {
        return animes;
    }

    public class Anime {
        @SerializedName("id")
        private String animeID;

        @SerializedName("attributes")
        private Atributes atributos;

        public Atributes getAtributos(){
            return atributos;
        }
        public String getAnimeID(){
            return animeID;
        }
    }

    public class Atributes{

        @SerializedName("posterImage")
        private ImagenFondo fondo;
        @SerializedName("canonicalTitle")
        private String animeName;
        @SerializedName("episodeCount")
        private String animeCaps;
        @SerializedName("status")
        private String animeState;

        public String getAnimeName(){
            return animeName;
        }
        public String getAnimeCaps(){
            return animeCaps;
        }
        public String getAnimeState(){
            return animeState;
        }
        public ImagenFondo getFondo(){
            return fondo;
        }

    }
    public class ImagenFondo{
        @SerializedName("original")
        private String URLImagen;

        public String getURLImagen(){
            return URLImagen;
        }
    }

}
