package com.example.jabra.omdbapplication.network;

import com.example.jabra.omdbapplication.domain.Movie;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by jabra on 30/10/2017.
 */


public interface MovieService {
    String KEY="BanMePlz";

    @GET("?apikey="+KEY)
    Call<Movie> getMovie(@Query("t") String txtSearch);




}
