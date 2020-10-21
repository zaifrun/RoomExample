package org.pondar.roomexample

import android.content.Context
import androidx.room.Room
import org.pondar.roomexample.models.BookDao

object Repository {

    lateinit var bookDao : BookDao
    var db : MyDataBase? = null


    fun initRepository(context: Context){
        if (db==null) {
            db = Room.databaseBuilder(
                context,
                MyDataBase::class.java, "mydatabase"
            ).build()
        }
        bookDao = db?.bookDao()!!

    }


}