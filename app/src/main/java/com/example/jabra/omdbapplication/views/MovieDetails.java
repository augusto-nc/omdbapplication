package com.example.jabra.omdbapplication.views;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.jabra.omdbapplication.MyApplication;
import com.example.jabra.omdbapplication.R;
import com.example.jabra.omdbapplication.databinding.ActivityMovieDetailsBinding;

import com.example.jabra.omdbapplication.domain.Movie;
import com.nostra13.universalimageloader.core.ImageLoader;

public class MovieDetails extends AppCompatActivity {

    public static String MOVIE="MOVIE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMovieDetailsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details);
       // setContentView(R.layout.activity_movie_details);
        Movie movie= (Movie) getIntent().getSerializableExtra(MOVIE);
        binding.setMovie(movie);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ImageLoader.getInstance().displayImage(movie.getPoster(),binding.img, MyApplication.getDisplayImageOptions(R.drawable.background).build());
    }

}
