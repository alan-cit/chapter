package com.alancamargo.chapter.data.repository

import com.alancamargo.chapter.data.api.CarApi
import com.alancamargo.chapter.data.mapping.toDomain
import com.alancamargo.chapter.domain.model.Car
import com.alancamargo.chapter.domain.repository.CarRepository
import javax.inject.Inject

class CarRepositoryImpl @Inject constructor(
    private val api: CarApi
) : CarRepository {

    override suspend fun getCars(): List<Car> {
        return api.getCars().map { it.toDomain() }
    }
}
