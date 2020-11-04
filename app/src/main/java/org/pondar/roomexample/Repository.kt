package org.pondar.roomexample

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import org.pondar.roomexample.models.*

object Repository {

    private lateinit var bookDao : BookDao
    private lateinit var publisherDao : PublisherDao
    private var db : MyDataBase? = null


    //This returns a LiveData object that we can observe
    //So this already happens in the background
    fun getAllBooks() : LiveData<List<Book>>
    {
        return bookDao.getAllBooks()
    }

    fun getAllPublishers() : LiveData<List<Publisher>>
    {
        return publisherDao.getAllPublishers()
    }

    fun getAllBooksFromPublisher(publisher:Publisher) : LiveData<List<PublishersWithBooks>>
    {
        return publisherDao.getPublisherWithBooks(publisher.publisherID)
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
        publisherDao.deleteAll()
    }

    suspend fun addBook(book: Book):Long {
        val rowNr = bookDao.addBook(book)
        return rowNr
    }


    suspend fun addPublisher(publisher:Publisher) : Long {
        return publisherDao.addPublisher(publisher)
    }

    fun initRepository(context: Context){
        if (db==null) {
            db = Room.databaseBuilder(
                context,
                MyDataBase::class.java, "mydatabase"
            ).fallbackToDestructiveMigration().build()
            //NOTICE - When we update the version of the database, it will be cleared now
            //There is a migration system, if you don't want that
        }
        bookDao = db?.bookDao()!!
        publisherDao = db?.publisherDao()!!

    }


}