package com.stopkaaaa.androidacademyproject.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.stopkaaaa.androidacademyproject.MovieClickListener
import com.stopkaaaa.androidacademyproject.data.models.Movie
import com.stopkaaaa.androidacademyproject.databinding.ViewHolderMovieBinding

class MovieListAdapter(private val movieClickListener: MovieClickListener) :
    RecyclerView.Adapter<MovieViewHolder>() {

    private var movies: List<Movie> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ViewHolderMovieBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.onBind(movies[position])
        holder.itemView.setOnClickListener {
            movieClickListener.movieClicked(position)
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun bindMovies(newList: List<Movie>?) {
        if (newList != null) {
            movies = newList
        }
    }
}

class MovieViewHolder(private val binding: ViewHolderMovieBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(movie: Movie) {
        binding.movie1Title.text = movie.title
        binding.movie1Genre.text = movie.genres.toString()
        binding.movie1Duration.text = "${movie.runtime} min"
        if (movie.adult) {
            binding.movie1AgeLimit.text = "16+"
        } else {
            binding.movie1AgeLimit.text = "13+"
        }
        Glide.with(binding.root.context)
            .load(movie.poster)
            .into(binding.movie1Poster)
        binding.movie1ReviewsCount.text = "${movie.votes} reviews"
        binding.movie1Rating.rating = movie.ratings
    }

}