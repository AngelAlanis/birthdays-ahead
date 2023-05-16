package com.example.birthdaysahead.model

import com.example.birthdaysahead.utils.formatListWithDash
import com.example.birthdaysahead.utils.formatListWithNumbers
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


data class Event(
    val name: String,
    val date: LocalDate,
    val color: Int,
    val typeOfEvent: TypeOfEvent,
    val note: String? = null,
    val giftWishes: List<String>? = emptyList(),
    val likes: List<String>? = emptyList(),
    val dislikes: List<String>? = emptyList()
) {
    private val format = "dd/MM/yyyy"
    private val formatter = DateTimeFormatter.ofPattern(format, Locale.getDefault())

    val iconChar: Char = name.first()
    val dateString: String = formatter.format(date).toString()
    val formattedWishes: String = giftWishes.formatListWithDash()
    val formattedLikes: String = likes.formatListWithNumbers()
    val formattedDislikes: String = dislikes.formatListWithNumbers()
}
