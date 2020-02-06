package com.arysugiarto.catalogmoviejetpack.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.arysugiarto.catalogmoviejetpack.R
import com.arysugiarto.catalogmoviejetpack.adapter.TvAdapter
import com.arysugiarto.catalogmoviejetpack.model.repo.remote.ListItem
import com.arysugiarto.catalogmoviejetpack.model.repo.remote.ListTv
import com.arysugiarto.catalogmoviejetpack.viewmodel.ViewModelFactory
import com.arysugiarto.catalogmoviejetpack.viewmodel.ViewModelTv
import kotlinx.android.synthetic.main.fragment_tv_show.*

class TvShowFragment : Fragment() {

    private var tvShowList = listOf<ListItem>()

    private val tvShowViewModel by lazy {
        val viewModelFactory= activity?.application?.let { ViewModelFactory.getInstance() }
        ViewModelProviders.of(this,viewModelFactory).get(ViewModelTv::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val tvShowsAdapter = TvAdapter(context)
        tvShowViewModel.tv.observe(viewLifecycleOwner, Observer {
            tv_progress_bar.visibility = View.GONE
            tvShowList = it
            tvShowsAdapter.addList(tvShowList)
        })
        rv_tv.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = tvShowsAdapter
        }
    }
}