package com.example.musicapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface Apiinterface {

    @Headers("X-RapidAPI-Key: e18f2c6236msh9692b99d8083fd1p1eb0ebjsn7abfb898c54b" , "X-RapidAPI-Host: deezerdevs-deezer.p.rapidapi.com")
    @GET("search")
    fun getDta(@Query("q")query: String):Call<MyDataX>      //MyDataX = also called pojo classes

}