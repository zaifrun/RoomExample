package org.pondar.roomexample

import androidx.room.Database
import androidx.room.RoomDatabase
import org.pondar.roomexample.models.Book
import org.pondar.roomexample.models.BookDao
import org.pondar.roomexample.models.Publisher
import org.pondar.roomexample.models.PublisherDao


@Database(entities = [Book::class,Publisher::class], version = 3)
abstract class MyDataBase :RoomDatabase(){
    abstract fun bookDao() : BookDao
    abstract fun publisherDao(): PublisherDao
}