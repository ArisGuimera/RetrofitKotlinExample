package com.cursokotlin.retrofitkotlinexample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), android.support.v7.widget.SearchView.OnQueryTextListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        searchCharapter.queryHint = "Ej: Hodor"
        searchCharapter.setOnQueryTextListener(this)
        doAsync {
            var call = getRetrofit().create(APIService::class.java).getCharacterByName("characters/hodor").execute()
            var character = call.body() as ModelResponse
            uiThread {
                initCharacter(character)
            }




        }

    }

    private fun initCharacter(character: ModelResponse) {

    }

    fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://api.got.show/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }


    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}
