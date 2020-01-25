package com.arysugiarto.catalogmoviejetpack.model.repo.remote

import com.google.gson.annotations.SerializedName

class CrewResponse (
    @SerializedName("crew")
    val crew: List<ListCrew>?
)