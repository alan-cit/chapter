package com.alancamargo.chapter.domain.usecase

import com.alancamargo.chapter.domain.model.Car
import com.alancamargo.chapter.domain.repository.CarRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCarsUseCaseImpl @Inject constructor(
    private val repository: CarRepository
) : GetCarsUseCase {

    override fun invoke(): Flow<List<Car>> = flow {
        val cars = repository.getCars()
        emit(cars)
    }
}
