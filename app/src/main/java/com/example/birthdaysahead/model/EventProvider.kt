package com.example.birthdaysahead.model

import android.graphics.Color
import java.time.LocalDate

object EventProvider {

    val events: List<Event> = listOf(
        Event(
            "Fernando",
            LocalDate.of(2023, 5, 5),
            Color.parseColor("#3764D8"),
            TypeOfEvent.BIRTHDAY
        ),

        Event(
            "María",
            LocalDate.of(2023, 5, 18),
            Color.parseColor("#AED837"),
            TypeOfEvent.BIRTHDAY
        ),

        Event(
            "Pedrito",
            LocalDate.of(2023, 5, 18),
            Color.parseColor("#D83737"),
            TypeOfEvent.BIRTHDAY
        ),

        Event(
            "Sergio",
            LocalDate.of(2023, 5, 7),
            Color.parseColor("#D837AB"),
            TypeOfEvent.BIRTHDAY
        ),

        Event(
            "Luis",
            LocalDate.of(2023, 5, 4),
            Color.parseColor("#D87137"),
            TypeOfEvent.BIRTHDAY
        ),

        Event(
            "Alberto",
            LocalDate.of(2023, 6, 2),
            Color.parseColor("#D87137"),
            TypeOfEvent.BIRTHDAY
        ),

        Event(
            "Baity",
            LocalDate.of(2023, 5, 10),
            Color.parseColor("#D1F437"),
            TypeOfEvent.BIRTHDAY
        ),

        Event(
            "Juan",
            LocalDate.of(2023, 5, 10),
            Color.parseColor("#DB5437"),
            TypeOfEvent.BIRTHDAY
        ),

        Event(
            "Felipe",
            LocalDate.of(2023, 5, 10),
            Color.parseColor("#D5F523"),
            TypeOfEvent.BIRTHDAY
        ),
    )
}