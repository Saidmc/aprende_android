package com.cognitus.myapplication2

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.cognitus.myapplication2.databinding.ItemDogBinding


class DogsAdapter(val images: List<String>) : RecyclerView.Adapter<DogsAdapter.ViewHolder>() {
    private var contexto: Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        contexto = parent.context
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDogBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(images[position])
        holder.binding.setClickListener {
            when (it!!.id) {
                holder.binding.ivDog.id -> {
                    Toast.makeText(
                        contexto,
                        "Toco la foto: " + images[position],
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    inner class ViewHolder(val binding: ItemDogBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(image: String) {
            binding.ivDog.fromUrl(image)
            binding.executePendingBindings()
        }
    }
}