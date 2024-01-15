package com.alancamargo.chapter.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.alancamargo.chapter.databinding.ItemCarBinding
import com.alancamargo.chapter.ui.model.UiCar

class CarViewHolder(
    private val binding: ItemCarBinding,
    private val onItemClicked: (UiCar) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bindTo(car: UiCar) = with(binding) {
        txtCarName.text = car.name
        root.setOnClickListener { onItemClicked(car) }
    }
}
