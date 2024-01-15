package com.alancamargo.chapter.ui.mapping

import com.alancamargo.chapter.domain.model.Car
import com.alancamargo.chapter.ui.model.UiCar

fun Car.toUi() = UiCar(id = id, name = name)
