package com.example.birthdaysahead.utils

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import com.example.birthdaysahead.R


fun changeBackgroundColor(context: Context, color: Int): Drawable? {
    val drawable = ContextCompat.getDrawable(context, R.drawable.day_profile_background)?.mutate()
    drawable?.setColorFilter(color, PorterDuff.Mode.SRC_IN)

    return drawable
}

fun List<String>?.formatListWithDash(): String {
    val formattedList = StringBuilder()
    if (this != null) {
        for (item in this) {
            formattedList.append("- $item\n")
        }
    }
    return formattedList.toString().trimEnd()
}

fun List<String>?.formatListWithNumbers(): String {
    val formattedList = StringBuilder()
    if (this != null) {
        for ((index, item) in this.withIndex()) {
            val number = index + 1
            formattedList.append("$number. $item\n")
        }
    }
    return formattedList.toString().trimEnd()
}

fun lightenColor(color: Int, ratio: Float): Int {
    return ColorUtils.blendARGB(color, 0xFFFFFFFF.toInt(), ratio)
}