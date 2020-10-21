package org.pondar.roomexample

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room

import org.pondar.roomexample.models.Book
import org.pondar.roomexample.models.BookDao

object Repository {

    private lateinit var bookDao : BookDao
    private var db : MyDataBase? = null


    fun getAllBooks() : LiveData<List<Book>>
    {
        return bookDao.getAllBooks()
    }

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