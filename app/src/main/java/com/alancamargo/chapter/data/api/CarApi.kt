package com.alancamargo.chapter.data.api

import com.alancamargo.chapter.data.model.CarResponse

interface CarApi {

    suspend fun getCars(): List<CarResponse>
}
