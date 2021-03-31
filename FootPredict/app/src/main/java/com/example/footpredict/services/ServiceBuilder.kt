package com.example.footpredict.services

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    private const val URL ="https://api-football-v1.p.rapidapi.com"
    //CREATE HTTP CLIENT
    private val okHttp = OkHttpClient.Builder().apply {
        addInterceptor(
            Interceptor { chain ->
                val builder = chain.request().newBuilder()
                builder.header("x-rapidapi-key", "ce54e36d64msh605fbf99d93fd76p148c19jsnf59cd9c166d8")
                builder.header("x-rapidapi-host", "api-football-v1.p.rapidapi.com")
                return@Interceptor chain.proceed(builder.build())
            }
        )
    }

    //retrofit builder
    private val builder = Retrofit.Builder().baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build())



    //create retrofit Instance
    private val retrofit = builder.build()

    //we will use this class to create an anonymous inner class function that
    //implements Country service Interface


    fun <T> buildService (serviceType :Class<T>):T{
        return retrofit.create(serviceType)
    }

}