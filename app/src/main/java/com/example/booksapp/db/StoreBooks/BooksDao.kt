package com.example.booksapp.db.StoreBooks

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BooksDao {
    @Query("select * from booksentity")
    fun getSavedBooks() : LiveData<List<BooksEntity>>

    @Insert
    suspend fun addBook(booksEntity: BooksEntity)

    @Query("Delete from booksentity where id=:id")
    suspend fun deleteBook(id:Int)

    @Query("SELECT * FROM booksentity WHERE uniqueId = :bookId LIMIT 1")
    suspend fun searchBookById(bookId: String): BooksEntity?
}