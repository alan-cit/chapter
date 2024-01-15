package com.alancamargo.chapter.data.api

import com.alancamargo.chapter.data.model.CarResponse
import javax.inject.Inject

class CarApiImpl @Inject constructor() : CarApi {

    override suspend fun getCars(): List<CarResponse> = listOf(
        CarResponse(id = 1, name = "Golzão quadrado"),
        CarResponse(id = 2, name = "Uno com escada"),
        CarResponse(id = 3, name = "Fusca"),
        CarResponse(id = 4, name = "Kombi"),
        CarResponse(id = 5, name = "Passat")
    )
}
