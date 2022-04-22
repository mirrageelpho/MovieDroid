package com.example.moviedroid.services.helper

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class FormatHelper {

    companion object {
        private var mDataFormater = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

        fun formatDate(date: String): String {
            val dt = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(date)
            return mDataFormater.format(dt)
        }

        suspend fun parseGenreString(str: String): String = withContext(Dispatchers.Default){
            val regex = Regex("[^A-Za-z0-9-, ]")
            regex.replace(str, "").replace(",", " |")
        }

        suspend fun stringToArray(str: String): List<String> = withContext(Dispatchers.Default){
            val regex = Regex("[^A-Za-z0-9-,]")
            regex.replace(str, "").split(",")
        }

    }
}
