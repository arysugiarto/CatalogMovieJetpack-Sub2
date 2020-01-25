package com.arysugiarto.catalogmoviejetpack.model.repo.remote

import com.google.gson.annotations.SerializedName

data class TvResponse (
    @SerializedName("crew")
    val crew: ListItem<ListCrew>
)