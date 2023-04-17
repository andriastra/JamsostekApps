package com.example.jamsostekapps.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jamsostekapps.R
import com.example.jamsostekapps.base.BaseRecyclerAdapter
import com.example.jamsostekapps.databinding.ItemServiceBinding
import com.example.jamsostekapps.domain.modul.Service

class ServiceAdapter: BaseRecyclerAdapter<Service>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_service, parent, false)
        )
    }

    inner class ViewHolder(itemView: View) : BaseViewHolder<Service>(itemView) {
        private val binding = ItemServiceBinding.bind(itemView)

        init {
            binding.root.setOnClickListener {
                onItemClicked?.invoke(masterList[adapterPosition])
            }
        }

        @SuppressLint("NotifyDataSetChanged")
        override fun bind(data: Service) {
            with(binding) {
                Glide.with(itemView.context).load(data.img).into(imgService)
            }
        }
    }
}