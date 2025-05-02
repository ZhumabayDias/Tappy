package com.tappy.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

//class Converters {
//    @TypeConverter
//    fun fromList(list: List<String>): String {
//        return Gson().toJson(list)
//    }
//
//    @TypeConverter
//    fun toList(json: String): List<String> {
//        val type = object : TypeToken<List<String>>() {}.type
//        return Gson().fromJson(json, type)
//    }
//}

class Converters {
    @TypeConverter
    fun fromList(list: List<String>): String = list.joinToString("|")

    @TypeConverter
    fun toList(data: String): List<String> = data.split("|")
}