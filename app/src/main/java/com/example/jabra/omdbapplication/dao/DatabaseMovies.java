package com.example.jabra.omdbapplication.dao;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.jabra.omdbapplication.domain.Movie;

/**
 * Created by jabra on 30/10/2017.
 */

@Database(entities = {Movie.class}, version = 3)
public abstract class DatabaseMovies extends RoomDatabase {

    private static DatabaseMovies INSTANCE;
    public abstract MovieDao MovieDao();
    public static DatabaseMovies getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DatabaseMovies.class, "movie-database")
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

}
