package com.stopkaaaa.androidacademyproject.ui.movieslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.stopkaaaa.androidacademyproject.data.paging.MoviesDataSourceFactory
import com.stopkaaaa.androidacademyproject.data.paging.PaginationState

class MoviesListPagedViewModel : ViewModel() {

    private var moviesDataSourceFactory: MoviesDataSourceFactory = MoviesDataSourceFactory()

    private val pagedConfig = PagedList.Config.Builder()
        .setPageSize(10)
        .setEnablePlaceholders(false)
        .setPrefetchDistance(4)
        .build()

    val moviesPagedLiveData = LivePagedListBuilder(moviesDataSourceFactory, pagedConfig).build()

    val paginationState: LiveData<PaginationState>? =
        Transformations.switchMap(moviesDataSourceFactory.moviesDataSourceLiveData) {
            it.paginationState
        }
}