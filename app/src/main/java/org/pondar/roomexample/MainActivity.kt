package org.pondar.roomexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.pondar.roomexample.models.Book

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRepository()
        addTestData()

        Repository.bookDao.getAllBooks().observe(this, {
            if (it.size>0) {
                Log.d("Loaded books:","book 1: "+it.get(0).toString())
            }
        })
    }



    fun addTestData()
    {
        val book1 = Book("123","War and Peace","Tolstoy",1800)
        Repository.bookDao.addBook(book1)
    }

    fun initRepository()
    {
        Repository.initRepository(this)

    }
}