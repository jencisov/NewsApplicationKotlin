package com.kuma.news.utils

import com.google.firebase.crash.FirebaseCrash
import java.text.ParseException
import java.text.SimpleDateFormat

/**
 * Created by Jorge.Enciso on 05/04/2017.
 */
object DateUtils {

    fun formatNewsApiDate(inputDate: String?): String {
        try {
            val inputDateFormat = "yyyy-MM-dd'T'HH:mm:sss'Z'"
            val outputDateFormat = "EEE, d MMM yyyy KK:mm"

            val inputFormat = SimpleDateFormat(inputDateFormat)
            val outputFormat = SimpleDateFormat(outputDateFormat)

            val date = inputFormat.parse(inputDate)
            return outputFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
            FirebaseCrash.report(e)
        }

        return inputDate!!
    }

}