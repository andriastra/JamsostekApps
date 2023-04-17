package com.example.jamsostekapps.ui.news

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jamsostekapps.R
import com.example.jamsostekapps.base.BaseRecyclerAdapter
import com.example.jamsostekapps.data.remote.Article
import com.example.jamsostekapps.databinding.ItemNewsBinding

class NewsAdapter: BaseRecyclerAdapter<Article>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        )
    }

    inner class ViewHolder(itemView: View) : BaseViewHolder<Article>(itemView) {
        private val binding = ItemNewsBinding.bind(itemView)

        init {
            binding.root.setOnClickListener {
                onItemClicked?.invoke(masterList[adapterPosition])
            }
        }

        @SuppressLint("NotifyDataSetChanged")
        override fun bind(data: Article) {
            with(binding) {
                Glide.with(itemView.context).load(data.urlToImage).into(imgNews)
                tvTitle.text = data.title
            }
        }
    }
}