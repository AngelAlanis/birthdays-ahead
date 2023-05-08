package com.example.birthdaysahead.utils

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.example.birthdaysahead.R


fun changeBackgroundColor(context: Context, color: Int): Drawable? {
    val drawable = ContextCompat.getDrawable(context, R.drawable.day_profile_background)?.mutate()
    drawable?.setColorFilter(color, PorterDuff.Mode.SRC_IN)

    return drawable
}
