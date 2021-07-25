package com.example.newwallpaper.retrofit

import com.example.newwallpaper.models.PhotoData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/")
    fun getPhotos(
        @Query("key") key: String,
        @Query("q") q:String,
        @Query("image_type") imageType:String,
        @Query("pretty") pretty:Boolean,

    ): Call<PhotoData>
}