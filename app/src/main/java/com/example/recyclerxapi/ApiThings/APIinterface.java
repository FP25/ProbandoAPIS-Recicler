package com.example.recyclerxapi.ApiThings;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIinterface {
    // Esto es pa poner en que se quiere buscar

    @GET("anime")
    Call<Animes>getAnimes();
    @GET("anime")
    Call<Animes>getAnimesByName(@Query("filter[text]") String anime);


    @GET("manga")
    Call<Animes>getMangas();
    @GET("manga")
    Call<Animes>getMangasByName(@Query("filter[text]") String manga);

}
