package com.example.birthdaysahead.model

import java.time.LocalDate

data class Event(
    val name: String,
    val date: LocalDate,
    val color: Int,
    val typeOfEvent: TypeOfEvent,
    val note: String,
    val giftWishes: List<String>,
    val likes: List<String>,
    val dislikes: List<String>
) {
    val iconChar: Char = name.first()
}
