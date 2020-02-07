package com.arysugiarto.catalogmoviejetpack.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.arysugiarto.catalogmoviejetpack.R
import com.arysugiarto.catalogmoviejetpack.adapter.MovieAdapter
import com.arysugiarto.catalogmoviejetpack.model.repo.remote.ListItem
import com.arysugiarto.catalogmoviejetpack.viewmodel.ViewModelFactory
import com.arysugiarto.catalogmoviejetpack.viewmodel.ViewModelMovies
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment : Fragment() {

    private var movie = listOf<ListItem>()

    private val movieViewModel by lazy {
        val viewModelFactory= activity?.application?.let { ViewModelFactory.getInstance() }
        ViewModelProviders.of(this,viewModelFactory).get(ViewModelMovies::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val movieAdapter = MovieAdapter(context)
            movieViewModel.movie.observe(viewLifecycleOwner, Observer {
            progressbar.visibility = View.GONE
            movie= it
            movieAdapter.addList(movie)
        })

        rv_movies.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = movieAdapter
        }

    }
}