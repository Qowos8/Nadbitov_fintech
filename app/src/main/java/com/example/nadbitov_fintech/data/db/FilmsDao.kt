package com.example.nadbitov_fintech.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(movies: List<FilmDb>)

    @Query("SELECT * FROM FilmDb")
    fun getAll(): Flow<List<FilmDb>>

    @Update
    suspend fun updateMovie(filmDb: FilmDb)

    @Query("SELECT * FROM FilmDb WHERE filmId=:filmId ")
    suspend fun getFilmsWithId(filmId: Int): List<FilmDb>
}