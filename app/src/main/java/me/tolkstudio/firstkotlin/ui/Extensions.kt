package me.tolkstudio.firstkotlin.ui

import android.content.Context
import androidx.core.content.ContextCompat
import me.tolkstudio.firstkotlin.R
import java.text.SimpleDateFormat
import java.util.*
import me.tolkstudio.firstkotlin.model.Color

const val DATE_TIME_FORMAT = "dd.MMM.yy HH:mm"

fun Date.format(): String = SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault()).format(this)

fun Context.dip(value: Int): Int = (value * resources.displayMetrics.density).toInt()

fun Color.getColorRes(): Int = when (this) {
    Color.WHITE -> R.color.white
    Color.RED -> R.color.color_red
    Color.GREEN -> R.color.color_green
    Color.BLUE -> R.color.color_blue

    else -> R.color.white
}

fun sortDescAndDistinctAndRemoteNulls(list: List<Int?>?): List<Int> =
    list?.filterNotNull()
            ?.distinct()
            ?.sorted()
            ?.reversed()
            ?: listOf()