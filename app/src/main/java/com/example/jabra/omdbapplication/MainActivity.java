package com.example.jabra.omdbapplication;

import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jabra.omdbapplication.dao.DatabaseMovies;
import com.example.jabra.omdbapplication.databinding.ItemMovieBinding;
import com.example.jabra.omdbapplication.domain.Movie;
import com.example.jabra.omdbapplication.views.MovieDetails;
import com.example.jabra.omdbapplication.views.MovieSearch;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,MovieSearch.MovieInteraction {


    RecyclerView recyclerView;


    List<Movie> movies;
    MovieAdapter movieAdapter;
    TextView textMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
        recyclerView= (RecyclerView) findViewById(R.id.list_view);
        textMessage= (TextView) findViewById(R.id.text_msg);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        movieAdapter=new MovieAdapter();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    protected void onStart() {
        super.onStart();
        movies = DatabaseMovies.getDatabase(this).MovieDao().getAll();
        textMessage.setVisibility(movies.size()==0 ? View.VISIBLE: View.GONE);
        recyclerView.setAdapter(movieAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.fab){
            MovieSearch movieSearch= new MovieSearch();
            movieSearch.show(getSupportFragmentManager(),"MOVIE");
        }

    }

    @Override
    public void onChange() {
        movies = DatabaseMovies.getDatabase(this).MovieDao().getAll();
        movieAdapter.notifyDataSetChanged();
        textMessage.setVisibility(View.GONE);
    }


    public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder>{


        @Override
        public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ItemMovieBinding movieBinding=ItemMovieBinding.inflate(getLayoutInflater(),parent,false);
            MovieHolder movieHolder= new MovieHolder(movieBinding);
            return movieHolder;
        }

        @Override
        public void onBindViewHolder(MovieHolder holder, int position) {
            final Movie m=movies.get(position);
            holder.itemMovieBinding.setMovie(m);
            ImageLoader.getInstance().displayImage(m.getPoster(),holder.itemMovieBinding.img,
                    MyApplication.getDisplayImageOptions(R.drawable.background).build());
            holder.itemMovieBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent(getApplicationContext(), MovieDetails.class);
                    intent.putExtra(MovieDetails.MOVIE,m);
                    startActivity(intent);
                }
            });

        }

        @Override
        public int getItemCount() {
            return movies.size();
        }

        public class MovieHolder extends RecyclerView.ViewHolder {
            ItemMovieBinding itemMovieBinding;
            public MovieHolder(ViewDataBinding itemView) {
                super(itemView.getRoot());
                itemMovieBinding= (ItemMovieBinding) itemView;
            }
        }

    }




}
