package com.alancamargo.chapter.domain.usecase

import com.alancamargo.chapter.domain.model.Car
import kotlinx.coroutines.flow.Flow

interface GetCarsUseCase {

    operator fun invoke(): Flow<List<Car>>
}
