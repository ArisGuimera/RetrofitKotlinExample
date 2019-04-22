package com.cursokotlin.retrofitkotlinexample

import com.google.gson.annotations.SerializedName

/**
 * Created by aristidesguimeraorozco on 29/4/18.
 */
data class DogsResponse (@SerializedName("status") var status:String, @SerializedName("message") var images: List<String>)