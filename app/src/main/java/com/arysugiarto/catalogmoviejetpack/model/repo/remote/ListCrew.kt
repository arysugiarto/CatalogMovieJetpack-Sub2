package com.arysugiarto.catalogmoviejetpack.model.repo.remote

import com.google.gson.annotations.SerializedName

data class ListCrew(
    @SerializedName("job")
    val job:String?,
    @SerializedName("name")
    val name:String?
)