package com.arysugiarto.catalogmoviejetpack.helper

import com.arysugiarto.catalogmoviejetpack.model.repo.remote.ItemResponse
import com.arysugiarto.catalogmoviejetpack.model.repo.remote.ListItem
import com.arysugiarto.catalogmoviejetpack.model.repo.remote.ListTv
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("movie/now_playing")
    fun getMovie(@Query("api_key") apiKey: String?) : Call<ItemResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") movieId: String?,
                        @Query("api_key") apiKey: String?
    ) : Call<ListItem>

    @GET("tv/popular")
    fun getTvShows(@Query("api_key") apiKey: String?) : Call<ItemResponse>


    @GET("tv/{tv_id}")
    fun getTvShowDetails(@Path("tv_id") tvId: String?,
                         @Query("api_key") apiKey: String?
    ) : Call<ListTv>

    @GET("movie/{movie_id}/credits")
    fun getMovieCrew(@Path("movie_id") movieId: String?,
                     @Query("api_key") apiKey: String?) : Call<TvResponse>

    @GET("tv/{tv_id}/credits")
    fun getTvShowCrew(@Path("tv_id") tvId: String?,
                      @Query("api_key") apiKey: String?) : Call<TvResponse>
}