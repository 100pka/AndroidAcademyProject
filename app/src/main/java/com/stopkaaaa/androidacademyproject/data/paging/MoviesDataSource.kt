package com.stopkaaaa.androidacademyproject.data.paging

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.stopkaaaa.androidacademyproject.data.MovieRepository
import com.stopkaaaa.androidacademyproject.data.models.Movie
import kotlinx.coroutines.*

class MoviesDataSource(
    private val repository: MovieRepository
) : PageKeyedDataSource<Int, Movie>() {

    private var job = SupervisorJob()
    private val scope = CoroutineScope(getJobErrorHandler() + job)
    private var retryQuery: (() -> Any)? = null

    private val _paginationState = MutableLiveData<PaginationState>()
    val paginationState: LiveData<PaginationState> get() = _paginationState

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movie>
    ) {
        retryQuery = { loadInitial(params, callback) }
        updateState(PaginationState.LOADING_INITIAL)
        executeQuery(1) {
            callback.onResult(it, null, 2)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {}

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        val page = params.key
        retryQuery = { loadAfter(params, callback) }

        executeQuery(page) {
            callback.onResult(it, page.plus(1))
        }
    }

    private fun executeQuery(
        page: Int,
        callback: (List<Movie>) -> Unit
    ) {
        scope.launch {
            if (page > 1) {
                updateState(PaginationState.LOADING_AFTER)
            }
            val result = repository.getPopularMoviesByPage(page)
            retryQuery = null
            val moviesId = result.body()
            var movies: List<Movie>? = null
            if (moviesId?.moviesIdList != null && moviesId.moviesIdList.isNotEmpty()) {
                movies = List(moviesId.moviesIdList.size) {
                    repository.getMovieById(moviesId.moviesIdList[it].id)
                }
                updateState(PaginationState.DONE)
            } else updateState(PaginationState.EMPTY)
            callback(movies?: listOf())
        }
    }

    private fun updateState(state: PaginationState) {
        _paginationState.postValue(state)
    }

    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, e ->
        Log.e(MoviesDataSource::class.java.simpleName, "An error happened: $e")
        updateState(PaginationState.ERROR)
    }
}