package com.example.android.catsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.android.catsapp.datalayer.catsbreeedsfeature.CatsApi
import com.example.android.catsapp.datalayer.catsbreeedsfeature.Order
import com.example.android.catsapp.datalayer.retrofitbuilder.ServiceBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launch {
            val api = ServiceBuilder.buildService(CatsApi::class.java)

            val response = api.getBreeds()

            if (response.isSuccessful && response.body() != null) {
                Log.e("onStart", response.body().toString())
                Log.e("onStart", response.raw().request.url.toString())
                Log.e("onStart", response.raw().request.headers.toString())
            } else {
                Log.e("onStart", "${response.code()} ${response.errorBody().toString()}")
            }
        }
    }
}