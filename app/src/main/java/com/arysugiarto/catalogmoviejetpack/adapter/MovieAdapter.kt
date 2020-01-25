package com.arysugiarto.catalogmoviejetpack.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arysugiarto.catalogmoviejetpack.BuildConfig
import com.arysugiarto.catalogmoviejetpack.R
import com.arysugiarto.catalogmoviejetpack.model.repo.remote.ListItem
import com.arysugiarto.catalogmoviejetpack.ui.DetailActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_list.view.*

class MovieAdapter (private val context: Context?): RecyclerView.Adapter<MovieAdapter.ViewHolder>(){

    private var movieList : List<ListItem> = emptyList()
    fun addList(modelMovie: List<ListItem>){
        this.movieList =modelMovie
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list, parent,false))
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(movieList[position])
        context?.let { Glide.with(it).load("${BuildConfig.IMG_URL}w500${movieList[position].posterPath}").into(holder.poster) }
        holder.cardItem.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("movie", movieList[position].id.toString())
            context?.startActivity(intent)
        }
    }

    class ViewHolder(itemView : View) :RecyclerView.ViewHolder(itemView) {
        val poster = itemView.image_cover
        val cardItem = itemView.card_item
        fun bindViewHolder(listMovies : ListItem){
            itemView.tv_title.text = listMovies.title
        }
    }
}