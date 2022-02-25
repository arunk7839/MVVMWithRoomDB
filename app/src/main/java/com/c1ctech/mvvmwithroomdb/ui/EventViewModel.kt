package com.c1ctech.mvvmwithroomdb

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.c1ctech.mvvmwithroomdb.data.db.EventDatabase
import com.c1ctech.mvvmwithroomdb.data.db.entity.Event
import com.c1ctech.mvvmwithroomdb.data.repository.EventRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EventViewModel(
    application: Application
) : AndroidViewModel(application) {


    val allEvents: LiveData<List<Event>>
    val repository: EventRepository

    // initialize dao, repository and all events
    init {
        val dao = EventDatabase.getDatabase(application).getEventsDao()
        repository = EventRepository(dao)
        allEvents = repository.getAllEvents()
    }

    fun insertEvent(event: Event) =
        viewModelScope.launch(Dispatchers.IO) { repository.insertEvent(event) }

    fun updateEvent(event: Event) =
        viewModelScope.launch(Dispatchers.IO) { repository.updateEvent(event) }

    fun deleteEvent(event: Event) =
        viewModelScope.launch(Dispatchers.IO) { repository.deleteEvent(event) }

     fun deleteEventById(id: Int) =
         viewModelScope.launch(Dispatchers.IO) { repository.deleteEventById(id) }

    fun clearEvent() =
        viewModelScope.launch(Dispatchers.IO) { repository.clearEvent() }

}