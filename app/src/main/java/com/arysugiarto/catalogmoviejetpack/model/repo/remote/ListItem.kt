package com.arysugiarto.catalogmoviejetpack.model.repo.remote

import com.google.gson.annotations.SerializedName

data class ListItem(

    @SerializedName("poster_path")
    val posterPath: String?,

    @SerializedName("overview")
    val overview: String?,

    @SerializedName("release_date")
    val release_date: String?,

    @SerializedName("id")
    val id: Int,

    @SerializedName("original_title")
    val original_title: String?,

    @SerializedName("original_language")
    val original_language: String?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("backdrop_path")
    val backdrop_path: String?,

    @SerializedName("vote_average")
    val vote_average: Double?,

    @SerializedName("name")
    val name: String?
)