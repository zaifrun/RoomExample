package org.pondar.roomexample.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "publishers")
data class Publisher(
    @PrimaryKey(autoGenerate = true)
    val publisherID: Long = 0,
    var country: String = "",
    var name: String = ""
)


