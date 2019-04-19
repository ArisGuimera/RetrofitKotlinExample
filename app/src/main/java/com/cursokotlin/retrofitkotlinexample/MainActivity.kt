package com.cursokotlin.retrofitkotlinexample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), android.support.v7.widget.SearchView.OnQueryTextListener {

    lateinit var imagesPuppies:List<String>
    lateinit var dogAdapter:DogAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        searchBreed.setOnQueryTextListener(this)
    }

    private fun initCharacter(puppies: ModelResponse) {
        if(puppies.status == "success"){
            imagesPuppies = puppies.images
        }
        dogAdapter = DogAdapter(imagesPuppies)
        rvDogs.setHasFixedSize(true)
        rvDogs.layoutManager = LinearLayoutManager(this)
        rvDogs.adapter = dogAdapter
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://dog.ceo/api/breed/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }


    override fun onQueryTextSubmit(query: String): Boolean {
        searchByName(query.toLowerCase())
        return true
    }

    private fun searchByName(query: String) {
        doAsync {
            val call = getRetrofit().create(APIService::class.java).getCharacterByName("$query/images").execute()
            val puppies = call.body() as ModelResponse
            uiThread {
                if(puppies.status == "success") {
                    initCharacter(puppies)
                }else{
                    showErrorDialog()
                }
                hideKeyboard()
            }
        }
    }

    private fun showErrorDialog() {
        alert("Ha ocurrido un error, inténtelo de nuevo.") {
            yesButton { }
        }.show()
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    private fun hideKeyboard(){
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(viewRoot.windowToken, 0)
    }
}