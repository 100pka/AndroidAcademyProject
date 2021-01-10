package com.stopkaaaa.androidacademyproject.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import coil.load
import com.stopkaaaa.androidacademyproject.BuildConfig
import com.stopkaaaa.androidacademyproject.ui.MovieClickListener
import com.stopkaaaa.androidacademyproject.R
import com.stopkaaaa.androidacademyproject.data.models.Movie
import com.stopkaaaa.androidacademyproject.databinding.ViewHolderMovieBinding

class MovieListAdapter(private val movieClickListener: MovieClickListener) :
    RecyclerView.Adapter<MovieViewHolder>() {

    private var movies: MutableList<Movie> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ViewHolderMovieBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.onBind(movies[position])
        holder.itemView.setOnClickListener {
            movieClickListener.movieClicked(movies[position].id)
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun bindMovies(newList: List<Movie>?) {
        if (newList != null) {
            movies.clear()
            movies.addAll(newList)
            notifyDataSetChanged()
        }
    }
}

class MovieViewHolder(private val binding: ViewHolderMovieBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(movie: Movie) {
        binding.movie1Title.text = movie.title
        binding.movie1Genre.text = movie.genres.toString()
            .subSequence(1, movie.genres.toString().length - 1)
        if (movie.runtime != null && movie.runtime != 0) {
            binding.movie1Duration.visibility = View.VISIBLE
            binding.movie1Duration.text =
                itemView.context.resources.getString(R.string.duration, movie.runtime)
        } else {
            binding.movie1Duration.visibility = View.INVISIBLE
        }
        if (movie.adult) {
            binding.movie1AgeLimit.text = itemView.context.resources.getString(R.string.age_adult)
        } else {
            binding.movie1AgeLimit.text =
                itemView.context.resources.getString(R.string.age_non_adult)
        }
        binding.movie1Poster.load(BuildConfig.TMDB_IMAGE_URL + movie.poster) {
            placeholder(R.drawable.background_poster_gradient)
            target(
                onStart = {
                    binding.movie1Poster.setImageDrawable(it)
                    binding.progressBar.visibility = View.VISIBLE
                },
                onSuccess = {
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.movie1Poster.setImageDrawable(it)
                },
                onError = {
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.movie1Poster.setImageDrawable(it)
                })
        }

        binding.movie1ReviewsCount.text =
            itemView.context.resources.getString(R.string.reviews, movie.votes)
        binding.movie1Rating.rating = (movie.ratings / 2).toFloat()
    }

}