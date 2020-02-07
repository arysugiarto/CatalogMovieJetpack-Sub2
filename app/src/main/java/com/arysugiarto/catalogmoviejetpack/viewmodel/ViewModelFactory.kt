package com.arysugiarto.catalogmoviejetpack.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arysugiarto.catalogmoviejetpack.di.Injection
import com.arysugiarto.catalogmoviejetpack.model.repo.DataRepo
@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val dataRepo: DataRepo) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(factoryClass: Class<T>) :T{
        return  when{
            factoryClass.isAssignableFrom(ViewModelMovies::class.java) -> ViewModelMovies(dataRepo) as T
            factoryClass.isAssignableFrom(ViewModelTv::class.java) -> ViewModelTv(dataRepo) as T
            else -> throw IllegalArgumentException("Unknown ViewModel: " + factoryClass.name)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory? {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Injection.movieRepository()?.let { ViewModelFactory(it) }
                    }
                }
            }
            return INSTANCE
        }

    }
}