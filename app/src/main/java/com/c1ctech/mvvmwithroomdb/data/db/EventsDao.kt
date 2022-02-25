package com.c1ctech.mvvmwithroomdb.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.c1ctech.mvvmwithroomdb.data.db.entity.Event

@Dao
interface EventsDao {

    // adds a new entry to our database.
    // if some data is same/conflict, it'll be replace with new data
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvent(event :Event)

    // deletes an event
    @Delete
    suspend fun deleteEvent(event: Event)

    // updates an event.
    @Update
    suspend fun updateEvent(event: Event)

    // read all the events from eventTable
    // and arrange events in ascending order
    // of their ids
    @Query("Select * from eventTable order by event_id ASC")
    fun getAllEvents(): LiveData<List<Event>>
    // why not use suspend ? because Room does not support LiveData with suspended functions.
    // LiveData already works on a background thread and should be used directly without using coroutines

    // delete all events
    @Query("DELETE FROM eventTable")
    suspend fun clearEvent()

    //you can use this too, to delete an event by id.
    @Query("DELETE FROM eventTable WHERE event_id = :id")
    suspend fun deleteEventById(id: Int)

}