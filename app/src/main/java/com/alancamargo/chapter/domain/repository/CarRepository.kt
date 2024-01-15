package com.alancamargo.chapter.domain.repository

import com.alancamargo.chapter.domain.model.Car

interface CarRepository {

    suspend fun getCars(): List<Car>
}
