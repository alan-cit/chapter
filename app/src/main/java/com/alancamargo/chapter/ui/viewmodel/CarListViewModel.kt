package com.alancamargo.chapter.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alancamargo.chapter.core.logging.Logger
import com.alancamargo.chapter.di.IoDispatcher
import com.alancamargo.chapter.domain.usecase.GetCarsUseCase
import com.alancamargo.chapter.ui.mapping.toUi
import com.alancamargo.chapter.ui.model.UiCar
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarListViewModel @Inject constructor(
    private val getCarsUseCase: GetCarsUseCase,
    private val logger: Logger,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _state = MutableStateFlow(CarListUiState())
    private val _action = MutableSharedFlow<CarListUiAction>()

    val state = _state.asStateFlow()
    val action = _action.asSharedFlow()

    fun getCars() {
        viewModelScope.launch(dispatcher) {
            getCarsUseCase().onStart {
                _state.update { it.onLoading() }
            }.onCompletion {
                _state.update { it.onFinishedLoading() }
            }.catch { throwable ->
                logger.error(message = "Deu ruim", throwable)
            }.collect { cars ->
                val uiCars = cars.map { it.toUi() }
                _state.update { it.onCarsReceived(uiCars) }
            }
        }
    }

    fun onCarItemClicked(car: UiCar) {
        val action = CarListUiAction.ShowCarDetails(car)
        sendAction(action)
    }

    private fun sendAction(action: CarListUiAction) = viewModelScope.launch(dispatcher) {
        _action.emit(action)
    }
}
