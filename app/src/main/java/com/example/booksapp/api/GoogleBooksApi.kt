package com.example.booksapp.api

import com.example.booksapp.models.apiModels.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleBooksApi {
    @GET("volumes")
    suspend fun getBooks(
        @Query("q") inputText : String,
//        @Query("key") apikey : String
    ) : Response<SearchResponse>
}