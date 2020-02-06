package com.arysugiarto.catalogmoviejetpack.ui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.arysugiarto.catalogmoviejetpack.BuildConfig
import com.arysugiarto.catalogmoviejetpack.R
import com.arysugiarto.catalogmoviejetpack.adapter.DetailPagerAdapter
import com.arysugiarto.catalogmoviejetpack.model.repo.remote.ListItem
import com.arysugiarto.catalogmoviejetpack.model.repo.remote.ListTv
import com.arysugiarto.catalogmoviejetpack.viewmodel.ViewModelFactory
import com.arysugiarto.catalogmoviejetpack.viewmodel.ViewModelMovies
import com.arysugiarto.catalogmoviejetpack.viewmodel.ViewModelTv
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import kotlinx.android.synthetic.main.activity_detail.*
import java.text.SimpleDateFormat

class DetailActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar

    private val movieDetailViewModel by lazy {
        val viewModelFactory= ViewModelFactory.getInstance()
        ViewModelProviders.of(this,viewModelFactory).get(ViewModelMovies::class.java)
    }

    private val tvShowDetailViewModel by lazy {
        val viewModelFactory= ViewModelFactory.getInstance()
        ViewModelProviders.of(this,viewModelFactory).get(ViewModelTv::class.java)
    }


    private fun dateConverter(date: String): String{
        val inputFormat = SimpleDateFormat("yyyy-MM-dd")
        val outputFormat = SimpleDateFormat("dd MMM yyyy")
        val inputDateStr = date
        val date = inputFormat.parse(inputDateStr)
        return outputFormat.format(date)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        toolbar = findViewById(R.id.toolbar)

        if (intent.getStringExtra("movie") != null){
            movieDetailViewModel.getMovieDetail(intent.getStringExtra("movie")).observe(this, Observer {
                initLoading("Loading your movie")
                loadDataMovie(it)
            })
        }else{
            tvShowDetailViewModel.getTvShowDetail(intent.getStringExtra("tvShow")).observe(this, Observer {
                initLoading("Loading your tv show")
                loadDataTvShow(it)
            })

        }

    }
    private fun loadDataMovie(movie : ListItem?){
        Glide.with(this).load("${BuildConfig.IMG_URL}w500${movie?.posterPath}").transform(
            RoundedCorners(15)
        ).into(iv_poster)
        tv_rating_item.text = movie?.vote_average.toString()
        tv_release_date.text = movie?.release_date?.let { dateConverter(it) }
        tv_title.text = movie?.title
        tv_desc.text = movie?.overview

    }

    private fun loadDataTvShow(tvShow: ListTv?){
        Glide.with(this).load("${BuildConfig.IMG_URL}w500${tvShow?.posterPath}").transform(
            RoundedCorners(15)
        ).into(iv_poster)
        tv_rating_item.text = tvShow?.vote_average.toString()
        tv_release_date.text = tvShow?.first_air_date?.let { dateConverter(it) }
        tv_title.text = tvShow?.name
        tv_desc.text = tvShow?.overview

    }

    private fun initLoading(msg: String){
//        detail_progress_bar.visibility = View.GONE
//        coordinatorLayout.visibility = View.VISIBLE
        tv_loading.visibility = View.GONE
    }
}
