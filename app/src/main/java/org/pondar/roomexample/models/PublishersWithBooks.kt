package org.pondar.roomexample.models

import androidx.room.Embedded
import androidx.room.Relation

data class PublishersWithBooks(
    @Embedded
    val publisher: Publisher,
    @Relation(
        parentColumn = "publisherID", //from publisher table
        entityColumn = "publisher_ID" //from books table
    )
    val books: List<Book>

)


