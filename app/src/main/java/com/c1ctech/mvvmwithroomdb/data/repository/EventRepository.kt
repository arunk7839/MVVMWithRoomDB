package com.c1ctech.mvvmwithroomdb.data.repository

import androidx.lifecycle.LiveData
import com.c1ctech.mvvmwithroomdb.data.db.EventsDao
import com.c1ctech.mvvmwithroomdb.data.db.entity.Event

class EventRepository(private val eventDao: EventsDao) {

    // get all the events
    fun getAllEvents(): LiveData<List<Event>> = eventDao.getAllEvents()

    // adds an event to our database.
    suspend fun insertEvent(event: Event) {
        eventDao.insertEvent(event)
    }

    // deletes an event from database.
    suspend fun deleteEvent(event: Event) {
        eventDao.deleteEvent(event)
    }

    // updates an event from database.
    suspend fun updateEvent(event: Event) {
        eventDao.updateEvent(event)
    }
    //delete an event by id.
    suspend fun deleteEventById(id: Int) = eventDao.deleteEventById(id)

    // delete all events
    suspend fun clearEvent() = eventDao.clearEvent()
}