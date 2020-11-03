package org.pondar.roomexample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import org.pondar.roomexample.models.Book

class MainActivity : AppCompatActivity() {

    lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Repository.initRepository(this)




        viewModel = ViewModelProvider(this)[MainViewModel::class.java]


        viewModel.addTestData()

        Repository.getAllBooks().observe(this, {
            if (it.size > 0) {
                Log.d("ReceivedData", "Data from database")
                for (book in it)
                    Log.d("Book:", book.toString())
            }
        })

        updateButton.setOnClickListener {
            val book = Book("123", "War and peace", "Tolstoy", 1840)
            viewModel.updateBook(book)

        }


    }

}