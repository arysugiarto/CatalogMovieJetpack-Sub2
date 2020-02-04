package com.arysugiarto.catalogmoviejetpack.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.arysugiarto.catalogmoviejetpack.model.repo.DataRepo
import com.arysugiarto.catalogmoviejetpack.model.repo.remote.ListItem

class ViewModelMovies(private val dataRepo: DataRepo): ViewModel() {
    val movie : LiveData<List<ListItem>> = dataRepo.getMovie()
    fun getMovieDetail(movieId:String): LiveData<ListItem> = dataRepo.getMovieDetail(movieId)
}