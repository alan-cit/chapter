package com.alancamargo.chapter.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.alancamargo.chapter.core.extensions.observeFlow
import com.alancamargo.chapter.core.tools.ToastHelper
import com.alancamargo.chapter.databinding.ActivityCarListBinding
import com.alancamargo.chapter.ui.adapter.CarAdapter
import com.alancamargo.chapter.ui.viewmodel.CarListUiAction
import com.alancamargo.chapter.ui.viewmodel.CarListUiState
import com.alancamargo.chapter.ui.viewmodel.CarListViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CarListActivity : AppCompatActivity() {

    private var _binding: ActivityCarListBinding? = null
    private val binding: ActivityCarListBinding
        get() = _binding!!

    private val viewModel by viewModels<CarListViewModel>()
    private val adapter by lazy { CarAdapter(viewModel::onCarItemClicked) }

    @Inject
    lateinit var toastHelper: ToastHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCarListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpUi()
        observeViewModelFlows()
        viewModel.getCars()
    }

    private fun setUpUi() {
        binding.recyclerView.adapter = adapter
    }

    private fun observeViewModelFlows() = with(viewModel) {
        observeFlow(state, ::onStateChanged)
        observeFlow(action, ::onAction)
    }

    private fun onStateChanged(state: CarListUiState) = with(state) {
        binding.progressBar.isVisible = isLoading
        cars?.let(adapter::submitList)
    }

    private fun onAction(action: CarListUiAction) = when (action) {
        is CarListUiAction.ShowCarDetails -> toastHelper.showToast(action.car.name)
    }
}
