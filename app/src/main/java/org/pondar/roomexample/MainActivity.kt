package org.pondar.roomexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.pondar.roomexample.models.Book

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRepository()
        addTestData()

        Repository.bookDao.getAllBooks().observe(this, {
            if (it.size>0) {
                Log.d("ReceivedData","Data from database")
                for (book in it)
                    Log.d("Book:",book.toString())
            }
        })
    }



    fun addTestData()
    {

        Log.d("AddTestData","Adding test data")
        val book1 = Book("1234","Brothers Karamazov","Dostojevski",1850)
        val book2 =  Book("123","War and peace","Tolstoy",1830)


        GlobalScope.launch(Dispatchers.IO) {

            Repository.bookDao.deleteAll()
            var id = Repository.bookDao.addBook(book1)
            Log.d("book added"," id = $id")

            id = Repository.bookDao.addBook(book2)
            Log.d("book added"," id = $id")



        }

    }

    fun initRepository()
    {
        Repository.initRepository(this)

    }
}