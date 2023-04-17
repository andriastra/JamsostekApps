package com.example.jamsostekapps.base

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    protected val masterList: MutableList<T> = mutableListOf()
    var onItemClicked:((T) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    open fun setData(newListData: MutableList<T>?) {
        if (newListData == null) return
        masterList.clear()
        masterList.addAll(newListData)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    open fun setListData(newListData: List<T>?) {
        if (newListData == null) return
        masterList.clear()
        masterList.addAll(newListData)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    open fun loadMoreData(listData: List<T>?) {
        if (listData == null) return
        masterList.addAll(listData)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(list: MutableList<T>) {
        masterList.clear()
        masterList.addAll(list)
        notifyDataSetChanged()
    }

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = masterList[position]
        (holder as BaseViewHolder<T>).bind(data)
    }

    override fun getItemCount(): Int = masterList.size

    abstract class BaseViewHolder<T>(val view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind(data: T)
    }
}