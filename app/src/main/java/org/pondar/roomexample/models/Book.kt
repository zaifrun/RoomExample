package org.pondar.roomexample.models

import android.graphics.Bitmap
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

/*
For using autogenerated primary keys that increment do this
@PrimaryKey(autoGenerate = true)
var id: Int
 */


@Entity(tableName = "books")
data class Book(
    @PrimaryKey var isbn: String = "",
    var title: String = "",
    var author: String = "",
    var publishedYear: Int,
    var publisher_ID:Long? = null
) {

    //This means that picture will NOT be in the database schema
    //which makes sense. Also it is not in constructor, because it
    // is added later
    @Ignore
    var picture: Bitmap? = null

    //A Foreign key
}