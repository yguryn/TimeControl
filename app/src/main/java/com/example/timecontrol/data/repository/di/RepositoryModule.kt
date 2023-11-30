package com.example.timecontrol.data.repository.di

import com.example.timecontrol.data.repository.DayRepositoryImpl
import com.example.timecontrol.domain.DayRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideDayRepository(dayRepositoryImpl: DayRepositoryImpl): DayRepository
}