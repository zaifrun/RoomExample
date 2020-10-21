package org.pondar.roomexample.models

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BookDao {

    @Query("SELECT * FROM books")
    fun getAllBooks():LiveData<List<Book>>

    @Query("SELECT * FROM books WHERE isbn=:isbnNr")
    fun getByIsbn(isbnNr: String):LiveData<Book>

    @Insert
    suspend fun addBook(book: Book): Long

    @Delete
    suspend fun deleteBook(book : Book):Int

    @Update
    suspend fun updateBook(book : Book):Int

    @Query("DELETE FROM books")
    suspend fun deleteAll():Int




}