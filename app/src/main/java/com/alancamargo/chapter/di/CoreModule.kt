package com.alancamargo.chapter.di

import com.alancamargo.chapter.core.logging.Logger
import com.alancamargo.chapter.core.logging.LoggerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Provides
    @Singleton
    fun provideLogger(): Logger = LoggerImpl()
}
