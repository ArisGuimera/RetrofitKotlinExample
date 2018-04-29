package com.cursokotlin.retrofitkotlinexample

import com.google.gson.annotations.SerializedName

/**
 * Created by aristidesguimeraorozco on 29/4/18.
 */
data class ModelResponse (var message:String, @SerializedName("data") var character: CharacterModel)
data class CharacterModel (var imageLink:String, var male:Boolean, var house:String, var name:String)