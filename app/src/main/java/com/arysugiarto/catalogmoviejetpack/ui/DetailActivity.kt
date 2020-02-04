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

    private lateinit var itemViewPager: ViewPager
    private lateinit var toolbar: Toolbar
    private lateinit var collapsingToolbar: CollapsingToolbarLayout
    private lateinit var appBar: AppBarLayout

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
        collapsingToolbar = findViewById(R.id.toolbar_layout)
        appBar = findViewById(R.id.app_bar)
        appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
            if (collapsingToolbar.height + verticalOffset < 2 * ViewCompat.getMinimumHeight(collapsingToolbar)) {
                toolbar.setNavigationIcon(R.drawable.ic_home_black_24dp)
                collapsingToolbar.setCollapsedTitleTextColor(Color.WHITE)
            } else {
                toolbar.setNavigationIcon(R.drawable.ic_home_black_24dp)
                collapsingToolbar.setExpandedTitleColor(Color.TRANSPARENT)
            }
        })

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }


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

        initViewPager()
    }

    private fun initViewPager(){
        itemViewPager = detail_view_pager
        itemViewPager.adapter = DetailPagerAdapter(supportFragmentManager)
        detail_tabLayout.setupWithViewPager(itemViewPager)
    }

    private fun loadDataMovie(movie : ListItem?){
        collapsingToolbar.title = movie?.title
        Glide.with(this).load("${BuildConfig.IMG_URL}w500${movie?.backdrop_path}").into(iv_poster_background)
        Glide.with(this).load("${BuildConfig.IMG_URL}w500${movie?.posterPath}").transform(
            RoundedCorners(15)
        ).into(iv_poster)
        tv_rating_item.text = movie?.vote_average.toString()
        tv_release_date.text = movie?.release_date?.let { dateConverter(it) }
        tv_title.text = movie?.title

    }

    private fun loadDataTvShow(tvShow: ListTv?){
        collapsingToolbar.title = tvShow?.name
        Glide.with(this).load("${BuildConfig.IMG_URL}w500${tvShow?.backdrop_path}").into(iv_poster_background)
        Glide.with(this).load("${BuildConfig.IMG_URL}w500${tvShow?.posterPath}").transform(
            RoundedCorners(15)
        ).into(iv_poster)
        tv_rating_item.text = tvShow?.vote_average.toString()
        tv_release_date.text = tvShow?.first_air_date?.let { dateConverter(it) }
        tv_title.text = tvShow?.name

    }

    private fun initLoading(msg: String){
        detail_progress_bar.visibility = View.GONE
        coordinatorLayout.visibility = View.VISIBLE
        tv_loading.visibility = View.GONE
    }
}
