package com.alancamargo.chapter.ui.viewmodel

import app.cash.turbine.test
import com.alancamargo.chapter.core.logging.Logger
import com.alancamargo.chapter.domain.model.Car
import com.alancamargo.chapter.domain.usecase.GetCarsUseCase
import com.alancamargo.chapter.ui.mapping.toUi
import com.alancamargo.chapter.ui.model.UiCar
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class CarListViewModelTest {

    private val mockGetCarsUseCase = mockk<GetCarsUseCase>()
    private val mockLogger = mockk<Logger>(relaxed = true)
    private val testDispatcher = StandardTestDispatcher()

    private val viewModel = CarListViewModel(
        mockGetCarsUseCase,
        mockLogger,
        testDispatcher
    )

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `getAllCars should set correct view states`() = runTest {
        // GIVEN
        val cars = stubCarList()
        every { mockGetCarsUseCase() } returns flowOf(cars)

        // WHEN
        viewModel.getCars()

        // THEN
        viewModel.state.test {
            skipItems(count = 1) // O estado inicial é irrelevante
            val loading = CarListUiState().onLoading()
            assertThat(awaitItem()).isEqualTo(loading)
            val uiCars = cars.map { it.toUi() }
            val carsReceived = loading.onCarsReceived(uiCars)
            assertThat(awaitItem()).isEqualTo(carsReceived)
            val finishedLoading = carsReceived.onFinishedLoading()
            assertThat(awaitItem()).isEqualTo(finishedLoading)
        }
    }

    @Test
    fun `getAllCars should log error`() = runTest {
        // GIVEN
        val exception = IOException()
        every { mockGetCarsUseCase() } returns flow { throw exception }

        // WHEN
        viewModel.getCars()

        // THEN
        advanceUntilIdle()
        verify { mockLogger.error(message = "Deu ruim", exception) }
    }

    @Test
    fun `onCarItemClicked should send ShowCarDetails action`() = runTest {
        // GIVEN
        val car = UiCar(id = 1, name = "Escort")

        // WHEN
        viewModel.onCarItemClicked(car)

        // THEN
        viewModel.action.test {
            val expected = CarListUiAction.ShowCarDetails(car)
            assertThat(awaitItem()).isEqualTo(expected)
        }
    }

    private fun stubCarList() = listOf(
        Car(id = 1, name = "Golf sapão"),
        Car(id = 2, name = "Escort"),
        Car(id = 3, name = "Kadett tubarão"),
        Car(id = 4, name = "Towner do cachorro quente")
    )
}
