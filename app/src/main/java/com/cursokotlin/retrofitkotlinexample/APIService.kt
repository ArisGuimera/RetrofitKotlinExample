package com.cursokotlin.retrofitkotlinexample

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * Created by aristidesguimeraorozco on 29/4/18.
 */
interface APIService {
    @GET
    fun getCharacterByName(@Url url:String): Call<DogsResponse>
}