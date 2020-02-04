package com.arysugiarto.catalogmoviejetpack.model.repo

import androidx.lifecycle.LiveData
import com.arysugiarto.catalogmoviejetpack.model.repo.remote.ListItem
import com.arysugiarto.catalogmoviejetpack.model.repo.remote.ListTv

interface DataSource {
    fun getMovie(): LiveData<List<ListItem>>
    fun getMovieDetail(movieId : String) : LiveData<ListItem>
    fun getTvShowsList(): LiveData<List<ListItem>>
    fun getTvShowsDetail(tvId:String) : LiveData<ListTv>
}