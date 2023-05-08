package com.example.birthdaysahead.model

import java.util.*

enum class TypeOfEvent {
    BIRTHDAY, ANNIVERSARY, EVENT;

    fun getFormattedName(): String {
        return name.lowercase()
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    }
}