package org.pondar.roomexample.models

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PublisherDao {

    @Transaction
    @Query("SELECT * FROM publishers")
    fun getPublishersWithBook(): List<PublishersWithBooks>

    @Transaction
    @Query("SELECT * FROM publishers WHERE publisherID=:id")
    fun getPublisherWithBooks(id:Long) : LiveData<List<PublishersWithBooks>>

    @Query("SELECT * FROM publishers")
    fun getAllPublishers(): LiveData<List<Publisher>>

    @Query("SELECT * FROM publishers WHERE publisherID=:id")
    fun getByID(id: Long): LiveData<Publisher>

    //Ignore means that we will ignore if we try to insert a
    //book with the same primary key
    //Alternatives are: Replace
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPublisher(publisher: Publisher) : Long

    @Delete
    suspend fun deletePublisher(publisher: Publisher) : Int

    @Update
    suspend fun updatePublisher(publisher: Publisher) : Int

    @Query("DELETE FROM publishers")
    suspend fun deleteAll() : Int
}