package com.alancamargo.chapter.ui.viewmodel

import com.alancamargo.chapter.ui.model.UiCar

sealed class CarListUiAction {

    data class ShowCarDetails(val car: UiCar) : CarListUiAction()
}
