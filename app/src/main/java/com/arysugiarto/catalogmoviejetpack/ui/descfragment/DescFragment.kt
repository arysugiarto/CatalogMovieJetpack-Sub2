package com.arysugiarto.catalogmoviejetpack.ui.descfragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.arysugiarto.catalogmoviejetpack.R
import com.arysugiarto.catalogmoviejetpack.model.repo.remote.ListItem
import com.arysugiarto.catalogmoviejetpack.model.repo.remote.ListTv
import com.arysugiarto.catalogmoviejetpack.viewmodel.ViewModelFactory
import com.arysugiarto.catalogmoviejetpack.viewmodel.ViewModelMovies
import com.arysugiarto.catalogmoviejetpack.viewmodel.ViewModelTv
import kotlinx.android.synthetic.main.fragment_desc.*


/**
 * A simple [Fragment] subclass.
 */
class DescFragment : Fragment() {

    private val movieDescriptionViewModel by lazy {
        val viewModelFactory= activity?.application?.let { ViewModelFactory.getInstance() }
        ViewModelProviders.of(this,viewModelFactory).get(ViewModelMovies::class.java)
    }

    private val tvShowDescriptionViewModel by lazy {
        val viewModelFactory= activity?.application?.let { ViewModelFactory.getInstance() }
        ViewModelProviders.of(this,viewModelFactory).get(ViewModelTv::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_desc, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity?.intent?.getStringExtra("movie") != null){
            movieDescriptionViewModel.getMovieDetail(activity?.intent?.getStringExtra("movie")!!)
                .observe(viewLifecycleOwner, Observer {
                    loadDataMovie(it)
                })
        }else{
            tvShowDescriptionViewModel.getTvShowDetail(activity?.intent?.getStringExtra("tvShow")!!)
                .observe(viewLifecycleOwner, Observer {
                    loadDataTvShow(it)
                })
        }

    }

    private fun loadDataMovie(movie : ListItem?){
        tv_desc.text = movie?.overview
    }

    private fun loadDataTvShow(tvShow: ListTv?){
        tv_desc.text = tvShow?.overview
    }


}
