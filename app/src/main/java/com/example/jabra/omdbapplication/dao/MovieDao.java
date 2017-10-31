package com.example.jabra.omdbapplication.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.jabra.omdbapplication.domain.Movie;

import java.util.List;

/**
 * Created by jabra on 30/10/2017.
 */


@Dao
public interface MovieDao {

    @Query("SELECT * FROM movie")
    List<Movie> getAll();

    @Insert
    void insert(Movie movie);

}
