package com.alancamargo.chapter.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.alancamargo.chapter.ui.model.UiCar

class CarDiffCallback : DiffUtil.ItemCallback<UiCar>() {

    override fun areItemsTheSame(oldItem: UiCar, newItem: UiCar): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UiCar, newItem: UiCar): Boolean {
        return oldItem == newItem
    }
}
