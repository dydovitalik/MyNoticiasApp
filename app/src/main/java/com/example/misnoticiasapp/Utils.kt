package com.example.misnoticiasapp

import org.ocpsoft.prettytime.PrettyTime
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object Utils {

    fun DateToTimeFormat(oldstringDate: String?): String? {
        val p = PrettyTime(Locale(country))
        var isTime: String? = null
        try {
            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
            val date = sdf.parse(oldstringDate)
            isTime = p.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return isTime
    }

    fun DateFormat(oldstringDate: String?): String? {
        val newDate: String?
        val dateFormat = SimpleDateFormat("HH:mm dd-MM-yyyy", Locale(country))
        newDate = try {
            val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(oldstringDate)
            dateFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
            oldstringDate
        }
        return newDate
    }

    val country: String
        get() {
            val locale = Locale.getDefault()
            val country = locale.country.toString()
            return country.lowercase()
        }
    val language: String
        get() {
            val locale = Locale.getDefault()
            val country = locale.language.toString()
            return country.lowercase()
        }
}