package com.example.booksapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.booksapp.db.StoreBooks.BooksDao
import com.example.booksapp.db.StoreBooks.BooksEntity


@Database(entities = [BooksEntity::class], version = 1)
abstract class BooksDatabase : RoomDatabase() {
    companion object{
        const val NAME = "Books_DB"
    }
    abstract fun getBooksDao () : BooksDao
}