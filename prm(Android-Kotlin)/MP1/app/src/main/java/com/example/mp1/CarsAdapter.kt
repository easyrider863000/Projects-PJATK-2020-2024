package com.example.mp1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mp1.databinding.ListItemBinding

class CarViewHolder(val binding: ListItemBinding)
    :RecyclerView.ViewHolder(binding.root){
        fun bind(car: Car){
            binding.name.text = car.name
            binding.image.setImageResource(car.resId)
        }
    }

class CarsAdapter:RecyclerView.Adapter<CarViewHolder>() {
    private val data = mutableListOf<Car>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val binding = ListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CarViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun replace(newData: List<Car>){
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }
}