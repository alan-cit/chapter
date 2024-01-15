package com.alancamargo.chapter.ui.viewmodel

import com.alancamargo.chapter.ui.model.UiCar

data class CarListUiState(
    val isLoading: Boolean = false,
    val cars: List<UiCar>? = null
) {

    fun onLoading() = copy(isLoading = true, cars = null)

    fun onFinishedLoading() = copy(isLoading = false)

    fun onCarsReceived(cars: List<UiCar>) = copy(cars = cars)
}
