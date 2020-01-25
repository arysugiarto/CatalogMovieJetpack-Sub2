package com.arysugiarto.catalogmoviejetpack.model.repo.remote

import com.google.gson.annotations.SerializedName

data class ItemResponse(
    @SerializedName("results")
    val result: List<ListItem>?
)