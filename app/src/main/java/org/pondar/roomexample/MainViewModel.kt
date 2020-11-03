package org.pondar.roomexample

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.pondar.roomexample.models.Book

class MainViewModel(application : Application): AndroidViewModel(application) {


    fun updateBook(book:Book){
        viewModelScope.launch(Dispatchers.IO) {
            Repository.updateBook(book)
        }
    }

    fun addTestData() {

        Log.d("AddTestData", "Adding test data")
        val book1 = Book("1234", "Brothers Karamazov", "Dostojevski", 1850)
        val book2 = Book("123", "War and peace", "Tolstoy", 1830)

       viewModelScope.launch(Dispatchers.IO) {

            Repository.deleteAll()
            var id = Repository.addBook(book1)
            Log.d("book added", " id = $id")

            id = Repository.addBook(book2)
            Log.d("book added", " id = $id")

        }

    }


}