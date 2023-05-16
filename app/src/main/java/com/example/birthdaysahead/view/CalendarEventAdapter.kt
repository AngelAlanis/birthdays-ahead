package com.example.birthdaysahead.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.birthdaysahead.R
import com.example.birthdaysahead.databinding.CalendarSelectedEventBinding
import com.example.birthdaysahead.model.Event
import com.example.birthdaysahead.model.EventProvider.events
import com.example.birthdaysahead.utils.changeBackgroundColor

class CalendarEventAdapter(private val listener: OnItemClickListener) :
    RecyclerView.Adapter<CalendarEventAdapter.CalendarEventHolder>() {

    val eventsList = mutableListOf<Event>()

    interface OnItemClickListener {
        fun onItemClick(event: Event)
    }


    inner class CalendarEventHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = CalendarSelectedEventBinding.bind(view)
        private val context = view.context

        init {
            itemView.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val event = eventsList[position]
                    listener.onItemClick(event)
                }
            }
        }

        fun bind(event: Event) {
            binding.profileIcon.background = changeBackgroundColor(context, event.color)
            binding.profileChar.text = event.iconChar.toString()
            binding.profileText.text = context.getString(R.string.profile_birthday, event.name)
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CalendarEventHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.calendar_selected_event, parent, false)
        return CalendarEventHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: CalendarEventHolder, position: Int) {
        val event = eventsList[position]
        holder.bind(event)
    }

    override fun getItemCount(): Int = eventsList.size

}