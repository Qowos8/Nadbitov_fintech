package com.example.nadbitov_fintech.di

import android.content.Context
import androidx.room.Room
import com.example.nadbitov_fintech.data.api.AppDatabase
import com.example.nadbitov_fintech.data.api.RoomConverter
import com.example.nadbitov_fintech.data.db.FilmsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Singleton
    @Provides
    fun provideDb(@ApplicationContext context: Context): AppDatabase  {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "database-name"
        ).addTypeConverter(RoomConverter()).build()
    }

    @Provides
    fun provideDao(database: AppDatabase) : FilmsDao {
        return database.filmsDao()
    }
}