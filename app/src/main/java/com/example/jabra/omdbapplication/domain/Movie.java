package com.example.jabra.omdbapplication.domain;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by jabra on 30/10/2017.
 */

@Entity(tableName = "movie")
public class Movie implements Serializable {



    @SerializedName("imdbID")
    @PrimaryKey
    private String primaryKey;

    @SerializedName("Title")
    @ColumnInfo(name = "title")
    private String title;


    @SerializedName("Runtime")
    @ColumnInfo(name = "duration")
    private String duration;

    @SerializedName("Genre")
    @ColumnInfo(name = "genre")
    private String genre;

    @SerializedName("Poster")
    @ColumnInfo(name = "poster")
    private String poster;

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    @SerializedName("Released")
    @ColumnInfo(name = "released")
    private String released;

    @SerializedName("Actors")
    @ColumnInfo(name = "actors")
    private String actors;


    public Movie(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
