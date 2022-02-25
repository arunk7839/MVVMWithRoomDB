package com.c1ctech.mvvmwithroomdb

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.c1ctech.mvvmwithroomdb.data.db.entity.Event
import com.c1ctech.mvvmwithroomdb.databinding.EventRvItemBinding

class EventAdapter(
    val eventDeleteIconClickInterface: EventDeleteIconClickInterface,
    val eventClickInterface: EventClickInterface
) : RecyclerView.Adapter<EventViewHolder>() {

    private val allEvents = ArrayList<Event>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {

        val inflater = LayoutInflater.from(parent.context)

        val binding = EventRvItemBinding.inflate(inflater, parent, false)
        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        // sets data to item of recycler view.
        holder.binding.tvTitle.text = allEvents.get(position).title
        holder.binding.tvDescription.text = allEvents.get(position).description

        // adding click listener to our delete image view icon.
        holder.binding.imgDelete.setOnClickListener {
            // call eventDeleteIconClickInterface.onEventDeleteIconClick() and pass position to it.
            eventDeleteIconClickInterface.onEventDeleteIconClick(allEvents.get(position))
        }

        // adding click listener to our recycler view item.
        holder.itemView.setOnClickListener {
            // call eventClickInterface.onEventClick() and pass position to it.
            eventClickInterface.onEventClick(allEvents.get(position))
        }
    }

    override fun getItemCount(): Int {
        // return list size.
        return allEvents.size
    }

    // update the list of events.
    fun updateList(newList: List<Event>) {

        // clear the allEvents array list
        allEvents.clear()

        // adds a new list to our allEvents list.
        allEvents.addAll(newList)


        // call notifyDataSetChanged() to notify our adapter.
        notifyDataSetChanged()
    }
}

interface EventDeleteIconClickInterface {
    // creating a method for click
    // action on delete image view.
    fun onEventDeleteIconClick(event: Event)
}

interface EventClickInterface {
    // creating a method for click action
    // on recycler view item for updating it.
    fun onEventClick(event: Event)
}

class EventViewHolder(val binding: EventRvItemBinding) : RecyclerView.ViewHolder(binding.root) {}