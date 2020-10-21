package org.pondar.roomexample

import androidx.room.Database
import androidx.room.RoomDatabase
import org.pondar.roomexample.models.Book
import org.pondar.roomexample.models.BookDao


@Database(entities = [Book::class], version = 1)
abstract class MyDataBase :RoomDatabase(){
    abstract fun bookDao() : BookDao
}