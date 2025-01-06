package com.example.booksapp.db.StoreBooks

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BooksEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int  = 0,
    val booksname : String,
    val link : String,
    val authors : String,
    val averageRating : String,
    val uniqueId : String,
    val description : String,
    val pageCount : Int,
    val language : String,
    val maturityRating : String,
    val category : String,
    val publisher : String,
    val publishedDate : String,
    val printType : String,
    val contentVersion : String,
    val infolink : String
)
