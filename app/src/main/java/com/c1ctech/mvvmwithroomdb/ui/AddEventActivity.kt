package com.c1ctech.mvvmwithroomdb

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.c1ctech.mvvmwithroomdb.data.db.entity.Event
import com.c1ctech.mvvmwithroomdb.databinding.ActivityAddEventBinding

class AddEventActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddEventBinding
    lateinit var viewModal: EventViewModel
    var eventID = -1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // initializes the viewmodel.
        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(EventViewModel::class.java)


        // getting data passed via an intent.
        val eventType = intent.getStringExtra("eventType")
        if (eventType.equals("Edit")) {
            // setting data to edit text.
            val eventTitle = intent.getStringExtra("eventTitle")
            val eventDescription = intent.getStringExtra("eventDescription")
            eventID = intent.getIntExtra("eventId", -1)
            binding.btnSave.setText("Update Event")
            binding.titleET.setText(eventTitle)
            binding.descriptionET.setText(eventDescription)
        } else {
            binding.btnSave.setText("Save Event")
        }

        // adding click listener to our save button.
        binding.btnSave.setOnClickListener {

            // getting title and desc from edit text.
            val eventTitle = binding.titleET.text.toString()
            val eventDescription = binding.descriptionET.text.toString()

            // checking the type and then saving or updating the data.
            if (eventType.equals("Edit")) {

                if (eventTitle.isNotEmpty() && eventDescription.isNotEmpty()) {

                    val updatedEvent = Event(eventID, eventTitle, eventDescription)
                    viewModal.updateEvent(updatedEvent)
                    Toast.makeText(this, "Event Updated", Toast.LENGTH_LONG).show()
                }
            } else {
                if (eventTitle.isNotEmpty() && eventDescription.isNotEmpty()) {

                    // if the string is not empty we are calling
                    // add event method to add data to our room database.
                    //why id null? because id is auto generate
                    viewModal.insertEvent(Event(null, eventTitle, eventDescription))
                    Toast.makeText(this, "Event Added", Toast.LENGTH_LONG).show()
                }
            }
            // opening the new activity
            startActivity(Intent(applicationContext, MainActivity::class.java))
            this.finish()
        }
    }
}