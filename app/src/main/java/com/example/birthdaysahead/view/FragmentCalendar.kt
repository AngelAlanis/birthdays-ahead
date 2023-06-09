package com.example.birthdaysahead.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.birthdaysahead.R
import com.example.birthdaysahead.databinding.CalendarDayLayoutBinding
import com.example.birthdaysahead.databinding.CalendarHeaderBinding
import com.example.birthdaysahead.databinding.FragmentCalendarBinding
import com.example.birthdaysahead.model.Event
import com.example.birthdaysahead.model.EventProvider
import com.example.birthdaysahead.utils.changeBackgroundColor
import com.example.birthdaysahead.viewmodel.EventViewModel
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.CalendarMonth
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.view.MonthDayBinder
import com.kizitonwose.calendar.view.MonthHeaderFooterBinder
import com.kizitonwose.calendar.view.ViewContainer
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

class FragmentCalendar : Fragment(), NewEventFragment.EventCreationListener, CalendarEventAdapter.OnItemClickListener {

    private var _binding: FragmentCalendarBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: EventViewModel by activityViewModels()

    private val eventsAdapter = CalendarEventAdapter(this)
    var events = mutableMapOf<LocalDate, List<Event>>()

    private val selectionFormatter = DateTimeFormatter.ofPattern("d MMM yyyy")
    private val titleFormatter = DateTimeFormatter.ofPattern("MMM yyyy")
    private val titleSameYearFormatter = DateTimeFormatter.ofPattern("MMMM")

    private val today = LocalDate.now()
    private var selectedDate: LocalDate? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        events = EventProvider.events.groupBy { it.date }.toMutableMap()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCalendarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.eventsRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = eventsAdapter
        }

        eventsAdapter.notifyDataSetChanged()

        setupCalendar()
    }

    private fun setupCalendar() {
        initListeners()

        val currentMonth = YearMonth.now()
        val daysOfWeek = daysOfWeek()
        val endMonth = currentMonth.plusMonths(50)
        val startMonth = currentMonth.minusMonths(50)

        configureBinders(daysOfWeek)

        binding.calendarView.apply {
            /*
            * Setup the Calendar with the min and max months, and the local first day of the week.
            * And Scroll to the current month.
            */
            setup(startMonth, endMonth, daysOfWeek.first())
            scrollToMonth(currentMonth)
        }

        selectDate(today)
    }

    private fun configureBinders(daysOfWeek: List<DayOfWeek>) {
        /*
        * Create the DayViewContainer which acts as a ViewHolder for each date cell.
        * The View passed in here is the provided inflated day view resource.
        */
        class DayViewContainer(view: View) : ViewContainer(view) {
            lateinit var day: CalendarDay // Will be set when this container is bound.
            val binding = CalendarDayLayoutBinding.bind(view)

            init {
                view.setOnClickListener {
                    // Check the day position as we do not want to select in or out dates.
                    if (day.position == DayPosition.MonthDate) {
                        selectDate(day.date)
                        updateAdapterForDate(day.date)
                    }
                }
            }
        }


        /*
        * MonthDayBinder that takes the logic of the DayViewContainer.
        * Applies the logic to all the days of the month.
        */
        binding.calendarView.dayBinder = object : MonthDayBinder<DayViewContainer> {
            override fun bind(container: DayViewContainer, data: CalendarDay) {
                container.day = data

                val context = binding.calendarView.context

                val parentLayout = container.binding.parentDay
                val textView = container.binding.calendarDayText

                val profileView1 = container.binding.profileView1
                val profileView2 = container.binding.profileView2
                val profileView3 = container.binding.profileView3
                val profileView4 = container.binding.profileView4

                // Put the views in a list to iterate through them.
                val profileViews = listOf(profileView1, profileView2, profileView3, profileView4)

                textView.text =
                    data.date.dayOfMonth.toString() // Set the number of day for the current day of the month.

                // Validation so in or out dates are not affected.
                if (data.position == DayPosition.MonthDate) {
                    textView.setTextColor(
                        ContextCompat.getColor(
                            textView.context,
                            R.color.text_grey_light
                        )
                    )

                    val event = events[data.date]

                    setEventViewsInDay(event, profileViews, context)

                    setDayBackground(data, parentLayout)

                } else {
                    // If the date is in or out, set the color to a darker one and disable the lineView.
                    textView.setTextColor(
                        ContextCompat.getColor(
                            textView.context,
                            R.color.text_grey
                        )
                    )
                    profileViews.forEach { view ->
                        view.visibility = View.INVISIBLE
                    }
                }
            }

            override fun create(view: View): DayViewContainer = DayViewContainer(view)
        }

        class MonthViewContainer(view: View) : ViewContainer(view) {
            val weekHeaderLayout = CalendarHeaderBinding.bind(view).weekHeaderLayout.root
        }

        /*
        * Set the header with the week days to the calendar for every month.
        */
        binding.calendarView.monthHeaderBinder =
            object : MonthHeaderFooterBinder<MonthViewContainer> {
                override fun bind(container: MonthViewContainer, data: CalendarMonth) {
                    if (container.weekHeaderLayout.tag == null) {
                        container.weekHeaderLayout.tag = data.yearMonth
                        container.weekHeaderLayout.children.map { it as TextView }
                            .forEachIndexed { index, textView ->
                                textView.text = daysOfWeek[index].name.first().toString()
                            }
                    }
                }

                override fun create(view: View): MonthViewContainer = MonthViewContainer(view)
            }
    }

    private fun setEventViewsInDay(
        event: List<Event>?,
        profileViews: List<TextView>,
        context: Context,
    ) {
        // If there is at least one event.
        if (event != null) {
            event.forEachIndexed { index, event ->
                if (index < profileViews.size) {
                    profileViews[index].background = changeBackgroundColor(context, event.color)
                    profileViews[index].text = event.iconChar.toString()
                    profileViews[index].visibility = View.VISIBLE
                }
            }
            // Set the rest of the view invisible.
            for (i in event.size until profileViews.size) {
                profileViews[i].visibility = View.INVISIBLE
            }

            // For no events, set all the views as invisible.
        } else {
            profileViews.forEach { view ->
                view.visibility = View.INVISIBLE
            }
        }
    }

    private fun setDayBackground(
        data: CalendarDay,
        parentLayout: ConstraintLayout
    ) {
        when (data.date) {
            today -> {
                parentLayout.setBackgroundResource(R.drawable.background_today)
            }
            selectedDate -> {
                parentLayout.setBackgroundResource(R.drawable.background_selected)
            }
            else -> {
                parentLayout.setBackgroundResource(R.drawable.background_unselected_day)
            }
        }
    }

    private fun initListeners() {
        /**
         * Set the MonthScroll listener, it will make changes when the month is changed.
         */
        binding.calendarView.monthScrollListener = {
            /*
            * When the month is changed, select the first day by default.
            */

            binding.monthTextView.text = if (it.yearMonth.year == today.year) {
                titleSameYearFormatter.format(it.yearMonth)
            } else {
                titleFormatter.format(it.yearMonth)
            }

            selectedDate?.let { newDate ->
                selectedDate = null
                binding.calendarView.notifyDateChanged(newDate)
                updateAdapterForDate(null)
            }

            selectDate(it.yearMonth.atDay(1))
        }

        binding.fabNew.setOnClickListener {
            val fragmentNewEvent = NewEventFragment.newInstance(selectedDate!!)
            fragmentNewEvent.setEventCreationListener(this)

            val transaction = parentFragmentManager.beginTransaction()
                .add(R.id.nav_host_fragment, fragmentNewEvent)
                .addToBackStack(null)

            transaction.commit()
        }
    }

    private fun updateAdapterForDate(date: LocalDate?) {
        eventsAdapter.eventsList.clear()
        eventsAdapter.eventsList.addAll(events[date].orEmpty())
        eventsAdapter.notifyDataSetChanged()
    }

    private fun selectDate(date: LocalDate) {
        if (selectedDate != date) {
            val oldDate = selectedDate
            selectedDate = date
            oldDate?.let { binding.calendarView.notifyDateChanged(it) }
            binding.calendarView.notifyDateChanged(date)

            binding.tvSelectedDate.text = selectionFormatter.format(date)
        }
    }

    private fun addEventToMap(event: Event) {
        val eventDate = event.date
        events[eventDate] = events[eventDate].orEmpty().plus(event)
        updateAdapterForDate(eventDate)

        _binding?.calendarView?.notifyDateChanged(eventDate)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onEventCreated(event: Event) {
        addEventToMap(event)
    }

    override fun onItemClick(event: Event) {
        sharedViewModel.setEvent(event)

        val action = FragmentCalendarDirections.actionFragmentCalendarToEventDetailsFragment()
        view?.findNavController()?.navigate(action)
    }
}