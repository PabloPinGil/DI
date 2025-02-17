package com.example.applistapeliculas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<Movie> movies;
    private Context context;

    public MovieAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);

        holder.titleTextView.setText(movie.getTitle());
        holder.yearTextView.setText(String.valueOf(movie.getYear()));
        holder.directorTextView.setText(movie.getDirector());
        holder.descriptionTextView.setText(movie.getDescription());

        Glide.with(context)
                .load(movie.getUrl())
                .into(holder.posterImageView);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, yearTextView, directorTextView, descriptionTextView;
        ImageView posterImageView;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.tvTitle);
            yearTextView = itemView.findViewById(R.id.tvYear);
            directorTextView = itemView.findViewById(R.id.tvDirector);
            descriptionTextView = itemView.findViewById(R.id.tvDescription);
            posterImageView = itemView.findViewById(R.id.ivPoster);
        }
    }
}
