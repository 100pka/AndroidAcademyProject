package com.stopkaaaa.androidacademyproject.adapters

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.stopkaaaa.androidacademyproject.BuildConfig
import com.stopkaaaa.androidacademyproject.R
import com.stopkaaaa.androidacademyproject.data.models.Movie
import com.stopkaaaa.androidacademyproject.databinding.ViewHolderMovieBinding
import com.stopkaaaa.androidacademyproject.ui.MovieClickListener

class MoviesPagedListAdapter(private val movieClickListener: MovieClickListener) :
PagedListAdapter<Movie, MovieViewHolder>(diffUtilCallback){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ViewHolderMovieBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let { movie ->
            holder.onBind(movie)
            holder.itemView.setOnClickListener {
                movieClickListener.movieClicked(movie.id)
            }
        }
    }

    companion object {
        private val diffUtilCallback = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
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
            placeholder(R.drawable.movie_list_poster_placeholder)
            error(R.drawable.movie_list_poster_placeholder)
            scale(Scale.FILL)
            target(
                onStart = {
                    binding.movie1Poster.setImageDrawable(it)
                    binding.progressBar.isVisible = true
                },
                onSuccess = {
                    binding.progressBar.isVisible = false
                    binding.movie1Poster.setImageDrawable(it)
                },
                onError = {
                    binding.progressBar.isVisible = false
                })
        }
        binding.movie1ReviewsCount.text =
            itemView.context.resources.getString(R.string.reviews, movie.votes)
        binding.movie1Rating.rating = (movie.ratings / 2).toFloat()
    }
}