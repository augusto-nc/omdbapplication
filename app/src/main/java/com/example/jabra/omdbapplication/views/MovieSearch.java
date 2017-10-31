package com.example.jabra.omdbapplication.views;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jabra.omdbapplication.MyApplication;
import com.example.jabra.omdbapplication.R;
import com.example.jabra.omdbapplication.dao.DatabaseMovies;
import com.example.jabra.omdbapplication.domain.Movie;
import com.example.jabra.omdbapplication.network.Retrofit;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by jabra on 30/10/2017.
 */

public class MovieSearch extends DialogFragment implements View.OnClickListener {


    public MovieSearch() {
        super();
    }


    EditText editText;
    View btAdd;
    View btSearch;
    View progressView;
    TextView textTitle;
    ImageView imageView;

    MovieInteraction movieInteraction;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);
        View v=inflater.inflate(R.layout.dialog_movie_search,container,false);
        editText= (EditText) v.findViewById(R.id.edit_query);
        btSearch= v.findViewById(R.id.bt_search);
        btSearch.setOnClickListener(this);
        progressView=v.findViewById(R.id.progressBar);
        btAdd=v.findViewById(R.id.bt_add);
        btAdd.setOnClickListener(this);

        progressView.setVisibility(View.GONE);
        imageView= (ImageView) v.findViewById(R.id.img);
        textTitle= (TextView) v.findViewById(R.id.title);
        return v;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog d= super.onCreateDialog(savedInstanceState);
        d.setTitle(R.string.buscar_filmes);
        return d;
    }

    Movie movie;


    private void refresh(){

        String url=null;
        if(movie==null){
            textTitle.setVisibility(View.GONE);
            btAdd.setVisibility(View.GONE);
        }else{
            textTitle.setVisibility(View.VISIBLE);
            textTitle.setText(movie.getTitle());
            btAdd.setVisibility(View.VISIBLE);
           url= movie.getPoster();
        }
        ImageLoader.getInstance().displayImage(url,imageView, MyApplication.getDisplayImageOptions(R.drawable.background).build());
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof MovieInteraction){
            movieInteraction= (MovieInteraction) context;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        refresh();
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.bt_search){
            if(!editText.getText().toString().isEmpty()){
                Retrofit retrofit = new Retrofit();
                btSearch.setEnabled(false);
                progressView.setVisibility(View.VISIBLE);
                movie=null;
                refresh();
                retrofit.getMovie(editText.getText().toString(), new Retrofit.MovieResult() {
                    @Override
                    public void onResult(Movie movie) {
                        MovieSearch.this.movie=movie;
                        btSearch.setEnabled(true);
                        if(movie==null){
                            Toast.makeText(getContext(),R.string.sem_resultados,Toast.LENGTH_LONG).show();
                        }
                        refresh();
                        progressView.setVisibility(View.GONE);
                    }
                });
                editText.setText("");
            }
        }else if(v.getId()==R.id.bt_add){
            DatabaseMovies.getDatabase(getContext()).MovieDao().insert(movie);
            dismiss();
            movieInteraction.onChange();
        }
    }


    public interface MovieInteraction{
        public void onChange();
    }

}
