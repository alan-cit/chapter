package com.alancamargo.chapter.di

import com.alancamargo.chapter.data.api.CarApi
import com.alancamargo.chapter.data.api.CarApiImpl
import com.alancamargo.chapter.data.repository.CarRepositoryImpl
import com.alancamargo.chapter.domain.repository.CarRepository
import com.alancamargo.chapter.domain.usecase.GetCarsUseCase
import com.alancamargo.chapter.domain.usecase.GetCarsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class CarsModule {

    @Binds
    @ViewModelScoped
    abstract fun bindCarApi(impl: CarApiImpl): CarApi

    @Binds
    @ViewModelScoped
    abstract fun bindCarRepository(impl: CarRepositoryImpl): CarRepository

    @Binds
    @ViewModelScoped
    abstract fun bindGetCarsUseCase(impl: GetCarsUseCaseImpl): GetCarsUseCase
}
