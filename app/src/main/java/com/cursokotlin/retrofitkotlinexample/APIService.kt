package com.cursokotlin.retrofitkotlinexample

import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by aristidesguimeraorozco on 29/4/18.
 */
interface APIService {
    @GET
    fun getCharacterByName(@Url url: String): Call<DogsResponse>

    @GET("/example/example2/{id}/loquesea")
    fun getCharacterByName2(@Path("id") id: String): Call<DogsResponse>

    @GET("/example/example2/v2/loquesea")
    fun getCharacterByName3(
        @Query("pet") pet: String,
        @Query("name") name: String
    ): Call<DogsResponse>

    @POST
    fun getEVERYTHING(@Body exampleArisDto: ExampleArisDto): Call<*>


    @Multipart
    @POST
    fun getEVERYTHING2(
        @Part image: MultipartBody.Part,
        @Part("example") myExample: String
    ): Call<*>

//    val requestBody =   RequestBody.create(MediaType.parse(getContentResolver().getType(fileUri)), file);
//    val a = MultipartBody.Part.createFormData("picture", file.getName(), requestBody);
}

data class ExampleArisDto(val name: String, val age: Int)