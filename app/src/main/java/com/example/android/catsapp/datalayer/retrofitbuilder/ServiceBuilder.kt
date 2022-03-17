package com.example.android.catsapp.datalayer.retrofitbuilder

import com.example.android.catsapp.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    private val client = OkHttpClient.Builder().apply {
        addInterceptor { chain ->
            val apiKey = BuildConfig.CATSAPI_KEY

            val original = chain.request()

            val request = original.newBuilder()
                .header("x-api-key", apiKey)
                .method(original.method, original.body)
                .build()

            chain.proceed(request)
        }
    }.build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.thecatapi.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun <T> buildService(service: Class<T>): T {
        return retrofit.create(service)
    }

}