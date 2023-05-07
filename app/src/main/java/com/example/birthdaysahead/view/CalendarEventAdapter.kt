package com.example.birthdaysahead.view

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.birthdaysahead.R
import com.example.birthdaysahead.databinding.CalendarSelectedEventBinding
import com.example.birthdaysahead.model.Event

class CalendarEventAdapter : RecyclerView.Adapter<CalendarEventAdapter.CalendarEventHolder>() {

    val eventsList = mutableListOf<Event>()

    class CalendarEventHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val binding = CalendarSelectedEventBinding.bind(view)
        private val context = view.context

        fun bind(event: Event) {
            binding.profileIcon.background = changeBackgroundColor(context, event.color)
            binding.profileChar.text = event.iconChar.toString()
            binding.profileText.text = context.getString(R.string.profile_birthday, event.name)
        }

        private fun changeBackgroundColor(context: Context, color: Int): Drawable? {
            val drawable =
                ContextCompat.getDrawable(context, R.drawable.day_profile_background)?.mutate()
            drawable?.setColorFilter(color, PorterDuff.Mode.SRC_IN)

            return drawable
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