package com.example.nadbitov_fintech.data.api

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.nadbitov_fintech.data.db.CountryDb
import com.example.nadbitov_fintech.data.db.GenresDb
import com.example.nadbitov_fintech.di.RetrofitModule.json
import kotlinx.serialization.encodeToString

@ProvidedTypeConverter
class RoomConverter {

    @TypeConverter
    fun stringToEGenres(string: String?): List<GenresDb>? {
        return string?.let { json.decodeFromString<List<GenresDb>>(it) }
    }

    @TypeConverter
    fun stringToCountries(string: String?): List<CountryDb>? {
        return string?.let { json.decodeFromString<List<CountryDb>>(it) }
    }

    @TypeConverter
    fun genresToEGenres(genres: List<GenresDb>?): String? {
        return genres?.let { json.encodeToString(it) }
    }

    @TypeConverter
    fun countriesToString(countries: List<CountryDb>?): String? {
        return countries?.let { json.encodeToString(it) }
    }
}