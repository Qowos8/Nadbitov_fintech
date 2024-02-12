package com.example.nadbitov_fintech.data.api

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.nadbitov_fintech.data.db.FilmDb
import com.example.nadbitov_fintech.data.db.FilmsDao

@Database(entities = [FilmDb::class], version = 1)
@TypeConverters(RoomConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun filmsDao(): FilmsDao
}