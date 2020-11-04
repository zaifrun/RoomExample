package org.pondar.roomexample

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.pondar.roomexample.models.Book
import org.pondar.roomexample.models.Publisher
import org.pondar.roomexample.models.PublishersWithBooks

class MainViewModel(application : Application): AndroidViewModel(application) {

    lateinit var books: List<Book>
    lateinit var publishers: List<Publisher>
    lateinit var publishersWithBook: List<PublishersWithBooks>


    fun updateBook(book:Book){
        viewModelScope.launch(Dispatchers.IO) {
            Repository.updateBook(book)
        }
    }

    fun addTestData() {


       viewModelScope.launch(Dispatchers.IO) {

            Repository.deleteAll()

           var publisher = Publisher(country = "Denmark",name="Gyldendal")
           var publisher2 = Publisher(country = "Denmark",name="Lindhart og Ringhof")

           val publisherID1 = Repository.addPublisher(publisher)
           Log.d("AddTestData", "Publisher id $publisherID1")
           val publisherID2 = Repository.addPublisher(publisher2)
           Log.d("AddTestData", "Publisher id $publisherID2")


           Log.d("AddTestData", "Adding test data")
           val book1 = Book("1234", "Brothers Karamazov", "Dostojevski", 1850,publisherID1)
           val book2 = Book("123", "War and peace", "Tolstoy", 1830,publisherID1)


           var id = Repository.addBook(book1)
            Log.d("book added", " id = $id")

            id = Repository.addBook(book2)
            Log.d("book added", " id = $id")

        }

    }


}