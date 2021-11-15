package org.pondar.roomexample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import org.pondar.roomexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Repository.initRepository(this)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        // To insert some test data - NOTE: also clears the database
       viewModel.addTestData()

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        Repository.getAllBooks().observe(this, Observer{
            if (it.size > 0) {
                viewModel.books = it
                //update UI
                Log.d("ReceivedData", "Books from database")
                var res = ""
                for (book in it) {
                    Log.d("Book:", book.toString())
                    res+=book.toString()+"\n\n"
                }
                binding.booksTextView.text = res
            }
        })

        Repository.getAllPublishers().observe(this, Observer{
            if (it.isEmpty())  //if no data - then just return.
                return@Observer
            Log.d("ReceivedData", "Publishers from database")
            viewModel.publishers = it
            //update UI here for instance.
            var res = ""
            for (publisher in it) {
                Log.d("Publisher:", publisher.toString())
                res+=publisher.toString()+"\n\n"
            }
            binding.publisherTextView.text = res

            Repository.getAllBooksFromPublisher(it.get(0)).observe(this,Observer {
                viewModel.publishersWithBook = it
                //update UI
                for (publisher in it) {
                    Log.d("Pubslisher_book_list", publisher.publisher.toString())
                    for (book in publisher.books)
                        Log.d("Book:", book.toString())
                }

            })
        })

        updateButton.setOnClickListener {
            val book = viewModel.books.get(0)
            book.publishedYear = 2020
            viewModel.updateBook(book)

        }


    }

}