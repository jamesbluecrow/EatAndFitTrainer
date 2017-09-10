package com.meslize.eatandfittrainer.ui.common

import android.support.v7.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<T, Holder : RecyclerView.ViewHolder>(
    val items: List<T>) : RecyclerView.Adapter<Holder>() {
  var onItemClickListener: OnItemClickListener? = null

  override fun getItemCount(): Int {
    return items.size
  }

  override fun getItemId(position: Int): Long {
    return position.toLong()
  }

  fun getItem(i: Int): T? {
    return items[i]
  }

  interface OnItemClickListener {
    fun onItemClicked(item: Any)
  }
}
