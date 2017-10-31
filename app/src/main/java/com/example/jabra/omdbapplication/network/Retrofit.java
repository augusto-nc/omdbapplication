package com.example.jabra.omdbapplication.network;

import com.example.jabra.omdbapplication.domain.Movie;

import java.util.MissingFormatArgumentException;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jabra on 30/10/2017.
 */

public class Retrofit {

    public  static final String BASE_URL= "http://www.omdbapi.com/";

    private static MovieService movieService;

    public interface MovieResult{
       void onResult(Movie movie);

    }

    public static MovieService getMovieService(){
        if(movieService==null) {
            retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
             movieService = retrofit.create(MovieService.class);
        }
        return  movieService;
    }

    public void getMovie(String textSearch, final MovieResult movieResult){

        Call<Movie> call= getMovieService().getMovie(textSearch);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Movie movie= response.body();
                if(movie.getTitle()!=null) {
                    movieResult.onResult(movie);
                }else{
                    movieResult.onResult(null);
                }
            }
            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                System.out.println("Error: ");
                movieResult.onResult(null);
            }
        });
    }
}
