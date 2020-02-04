package com.arysugiarto.catalogmoviejetpack.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.arysugiarto.catalogmoviejetpack.model.repo.DataRepo
import com.arysugiarto.catalogmoviejetpack.model.repo.remote.ListItem
import com.arysugiarto.catalogmoviejetpack.model.repo.remote.ListTv

class ViewModelTv(private val dataRepo: DataRepo): ViewModel() {
    val tv : LiveData<List<ListItem>> = dataRepo.getTvShowsList()
    fun getTvShowDetail(tvId:String):LiveData<ListTv> = dataRepo.getTvShowsDetail(tvId)
}