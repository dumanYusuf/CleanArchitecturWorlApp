package com.example.worldnewsapp.data.remote

import com.example.worldnewsapp.data.remote.dto.NewsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {



    //https://api.mediastack.com/v1/news?access_key=da5e8badd082f98ecabb144d6026e3d4&limit=50


    @GET("/v1/news?access_key=da5e8badd082f98ecabb144d6026e3d4&limit=50")
    suspend fun getNewsApi(
        @Query("key") key:String
    ): NewsDto

}