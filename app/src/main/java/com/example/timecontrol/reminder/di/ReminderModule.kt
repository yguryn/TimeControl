package com.example.timecontrol.reminder.di

import android.content.Context
import com.example.timecontrol.reminder.RemindersManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ReminderModule {

    @Provides
    @Singleton
    fun provideReminderManager(@ApplicationContext context: Context) = RemindersManager(context)

}