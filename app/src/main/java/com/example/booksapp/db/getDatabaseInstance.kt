package com.example.booksapp.db

import android.app.Application
import androidx.room.Room

class getDatabaseInstance : Application() {
    companion object{
        lateinit var booksDatabase : BooksDatabase
    }

    override fun onCreate() {
        super.onCreate()
        booksDatabase = Room.databaseBuilder(
            applicationContext,
            BooksDatabase::class.java,
            BooksDatabase.NAME
        ).build()
    }
}