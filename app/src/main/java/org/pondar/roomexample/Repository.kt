package org.pondar.roomexample

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room

import org.pondar.roomexample.models.Book
import org.pondar.roomexample.models.BookDao

object Repository {

    private lateinit var bookDao : BookDao
    private var db : MyDataBase? = null


    //This returns a LiveData object that we can observe
    //So this already happens in the background
    fun getAllBooks() : LiveData<List<Book>>
    {
        return bookDao.getAllBooks()
    }

    fun getByISBN(isbnNr: String) : LiveData<Book>
    {
        return bookDao.getByIsbn(isbnNr)
    }


    suspend fun updateBook(book:Book) : Int{
        return bookDao.updateBook(book)
    }


    //suspends indicates this cannot be called on the main thread
    suspend fun deleteAll() {
        bookDao.deleteAll()
    }

    suspend fun addBook(book: Book) {
        bookDao.addBook(book)
    }

    fun initRepository(context: Context){
        if (db==null) {
            db = Room.databaseBuilder(
                context,
                MyDataBase::class.java, "mydatabase"
            ).build()
        }
        bookDao = db?.bookDao()!!

    }


}