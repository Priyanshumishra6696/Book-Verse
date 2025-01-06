package com.example.booksapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val baseURL = "https://www.googleapis.com/books/v1/"
    private fun getInstance() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val googleBooksApi : GoogleBooksApi = getInstance().create(GoogleBooksApi::class.java)
}