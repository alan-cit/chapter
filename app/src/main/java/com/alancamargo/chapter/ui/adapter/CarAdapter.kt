package com.alancamargo.chapter.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.alancamargo.chapter.databinding.ItemCarBinding
import com.alancamargo.chapter.ui.model.UiCar

class CarAdapter(
    private val onItemClicked: (UiCar) -> Unit
) : ListAdapter<UiCar, CarViewHolder>(CarDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCarBinding.inflate(inflater, parent, false)
        return CarViewHolder(binding, onItemClicked)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val car = getItem(position)
        holder.bindTo(car)
    }
}
