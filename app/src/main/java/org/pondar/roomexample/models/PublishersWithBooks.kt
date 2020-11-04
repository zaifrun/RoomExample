package org.pondar.roomexample.models

import androidx.room.Embedded
import androidx.room.Relation

data class PublishersWithBooks(
    @Embedded
    val publisher: Publisher,
    @Relation(
        parentColumn = "publisherID",
        entityColumn = "publisher_ID"
    )
    val books: List<Book>

)


