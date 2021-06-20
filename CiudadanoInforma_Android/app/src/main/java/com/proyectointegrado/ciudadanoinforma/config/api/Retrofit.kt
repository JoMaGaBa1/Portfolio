package com.proyectointegrado.ciudadanoinforma.config.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object Retrofit {
    //Clase objeto para la conexion entre Android y la Api
    var gson = GsonBuilder()
        .setLenient()
        .create()
    private const val URL = "http://80.59.11.241:8088/api/"
    private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val okHttp = OkHttpClient.Builder().addInterceptor(logger)
    private val builder = Retrofit.Builder().baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttp.build())
    private val retrofit = builder.build()
    fun buildService(): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}