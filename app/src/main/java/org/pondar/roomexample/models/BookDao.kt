package org.pondar.roomexample.models

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BookDao {

    @Query("SELECT * FROM books")
    fun getAllBooks():LiveData<List<Book>>

    @Query("SELECT * FROM books WHERE isbn=:isbnNr")
    fun getByIsbn(isbnNr: String):LiveData<Book>

    //Ignore means that we will ignore if we try to insert a
    //book with the same primary key
    //Alternatives are: Replace
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addBook(book: Book) : Long

    @Delete
    suspend fun deleteBook(book : Book) : Int

    @Update
    suspend fun updateBook(book : Book) : Int

    @Query("DELETE FROM books")
    suspend fun deleteAll() : Int




}