package com.arysugiarto.catalogmoviejetpack.model.repo

import androidx.lifecycle.LiveData
import com.arysugiarto.catalogmoviejetpack.model.repo.remote.ListCrew
import com.arysugiarto.catalogmoviejetpack.model.repo.remote.ListItem
import com.arysugiarto.catalogmoviejetpack.model.repo.remote.ListTv

interface DataSource {
    fun getMovie(): LiveData<List<ListItem>>
    fun getDetailMovie(movieId : String) : LiveData<ListItem>
    fun getTv(): LiveData<List<ListItem>>
    fun getDetailTv(tvId:String) : LiveData<ListTv>
    fun getMovieCrew(movieId: String): LiveData<List<ListCrew>>
    fun getTvCrew(tvId: String): LiveData<List<ListCrew>>
}