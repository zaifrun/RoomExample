package org.pondar.roomexample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import org.pondar.roomexample.models.Book
import org.pondar.roomexample.models.Publisher
import org.pondar.roomexample.models.PublishersWithBooks

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    lateinit var books: List<Book>
    lateinit var publishers: List<Publisher>
    lateinit var publishersWithBook: List<PublishersWithBooks>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Repository.initRepository(this)




        viewModel = ViewModelProvider(this)[MainViewModel::class.java]


        viewModel.addTestData()

        Repository.getAllBooks().observe(this, {
            if (it.size > 0) {
                books = it
                //update UI
                Log.d("ReceivedData", "Data from database")
                for (book in it)
                    Log.d("Book:", book.toString())
            }
        })

        Repository.getAllPublishers().observe(this, {
            publishers = it
            //update UI
            for (publisher in it)
                Log.d("Pubsliher:", publisher.toString())


            Repository.getAllBooksFromPublisher(it.get(0)).observe(this, {
                publishersWithBook = it
                //update UI
                for (publisher in it) {
                    Log.d("Pubslisher:", publisher.publisher.toString())
                    for (book in publisher.books)
                    {
                        Log.d("Book:", book.toString())

                    }
                }

            })
        })





        updateButton.setOnClickListener {
            val book = Book("123", "War and peace", "Tolstoy", 1840)
            viewModel.updateBook(book)

        }


    }

}