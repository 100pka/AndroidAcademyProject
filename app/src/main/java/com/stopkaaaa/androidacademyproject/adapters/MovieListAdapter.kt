package com.stopkaaaa.androidacademyproject.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stopkaaaa.androidacademyproject.MovieClickListener
import com.stopkaaaa.androidacademyproject.R
import com.stopkaaaa.androidacademyproject.data.models.Movie
import com.stopkaaaa.androidacademyproject.databinding.ViewHolderMovieBinding

class MovieListAdapter(private val movieClickListener: MovieClickListener) : RecyclerView.Adapter<MovieViewHolder>() {

    private var movies: MutableList<Movie> = mutableListOf()

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

    fun bindMovies(newList: List<Movie>) {
        movies.clear()
        movies.addAll(newList)
        notifyDataSetChanged()
    }
}

class MovieViewHolder(private val binding: ViewHolderMovieBinding) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(movie: Movie) {
        binding.movie1Title.text = movie.title
        binding.movie1Genre.text = movie.genre
        binding.movie1Duration.text = binding.root.resources.getString(R.string.duration, movie.duration)
        binding.movie1AgeLimit.text = binding.root.resources.getString(R.string.age_limit, movie.ageLimit)
        binding.movie1Poster.setImageResource(movie.posterSmall)
        binding.movie1ReviewsCount.text = binding.root.resources.getString(R.string.reviews, movie.reviewsCount)
        binding.movie1Rating.rating = movie.rating.toFloat()
    }

}