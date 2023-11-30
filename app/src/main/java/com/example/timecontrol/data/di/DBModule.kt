package com.example.timecontrol.data.di

import android.content.Context
import androidx.room.Room
import com.example.timecontrol.data.db.DayControlDataBase
import com.example.timecontrol.data.db.DayDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DBModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): DayControlDataBase {
        return Room.databaseBuilder(context, DayControlDataBase::class.java, DATABASE_NAME)
            .addMigrations(DayControlDataBase.MIGRATION_1_2)
            .build()
    }

    @Provides
    @Singleton
    fun provideDayDao(dayControlDataBase: DayControlDataBase): DayDao {
        return dayControlDataBase.dayDao()
    }

    companion object {
        const val DATABASE_NAME = "DatabaseName"
    }
}