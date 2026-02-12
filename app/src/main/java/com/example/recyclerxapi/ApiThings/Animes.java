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

        @SerializedName("relationships")
        private String genero;

        @SerializedName("characters")
        private String personajes;

        @SerializedName("productions")
        private String productora;

        public Atributes getAtributos() {
            return atributos;
        }

        public String getAnimeID() {
            return animeID;
        }
    }

    public class Atributes {
        @SerializedName("posterImage")
        private PosterImag posterImag;
        @SerializedName("canonicalTitle")
        private String animeName;
        @SerializedName("episodeCount")
        private String animeCaps;
        @SerializedName("status")
        private String animeState;

        @SerializedName("coverImage")
        private CoverImag coverImag;
        @SerializedName("synopsis")
        private String synopsis;
        @SerializedName("description")
        private String description;
        @SerializedName("averageRating")
        private String averageRating;
        @SerializedName("startDate")
        private String startDate;
        @SerializedName("endDate")
        private String endDate;
        @SerializedName("popularityRank")
        private int popularityRank;
        @SerializedName("ratingRank")
        private int ratingRank;
        @SerializedName("ageRatingGuide")
        private String ageRatingGuide;
        @SerializedName("episodeLength")
        private int episodeLength;
        @SerializedName("totalLength")
        private int totalLength;

        public int getTotalLength() {
            return totalLength;
        }

        public void setTotalLength(int totalLength) {
            this.totalLength = totalLength;
        }

        public int getEpisodeLength() {
            return episodeLength;
        }

        public void setEpisodeLength(int episodeLength) {
            this.episodeLength = episodeLength;
        }

        public String getAgeRatingGuide() {
            return ageRatingGuide;
        }

        public void setAgeRatingGuide(String ageRatingGuide) {
            this.ageRatingGuide = ageRatingGuide;
        }

        public int getRatingRank() {
            return ratingRank;
        }

        public void setRatingRank(int ratingRank) {
            this.ratingRank = ratingRank;
        }

        public int getPopularityRank() {
            return popularityRank;
        }

        public void setPopularityRank(int popularityRank) {
            this.popularityRank = popularityRank;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getAverageRating() {
            return averageRating;
        }

        public void setAverageRating(String averageRating) {
            this.averageRating = averageRating;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getSynopsis() {
            return synopsis;
        }

        public void setSynopsis(String synopsis) {
            this.synopsis = synopsis;
        }

        public CoverImag getCoverImag() {
            return coverImag;
        }

        public void setCoverImag(CoverImag coverImag) {
            this.coverImag = coverImag;
        }

        public String getAnimeName() {
            return animeName;
        }

        public String getAnimeCaps() {
            return animeCaps;
        }

        public String getAnimeState() {
            return animeState;
        }

        public PosterImag getPosterImag() {
            return posterImag;
        }

    }

    public class PosterImag {
        @SerializedName("original")
        private String URLImagen;

        public String getURLImagen() {
            return URLImagen;
        }
    }

    public class CoverImag {
        @SerializedName("original")
        private String URLCoverImagen;

        public String getURLImagen() {
            return URLCoverImagen;
        }
    }
}
