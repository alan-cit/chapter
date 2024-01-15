package com.alancamargo.chapter.data.mapping

import com.alancamargo.chapter.data.model.CarResponse
import com.alancamargo.chapter.domain.model.Car

fun CarResponse.toDomain() = Car(id = id, name = name)
