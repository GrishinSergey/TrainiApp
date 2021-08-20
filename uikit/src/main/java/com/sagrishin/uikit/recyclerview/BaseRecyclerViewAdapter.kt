package com.sagrishin.uikit.recyclerview

import androidx.annotation.CallSuper
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<T> : RecyclerView.Adapter<BaseHolder<T>>() {

    protected abstract val items: MutableList<T>
    protected abstract val diffUtilCallback: DiffUtil.ItemCallback<T>
    protected abstract val differ: AsyncListDiffer<T>

    override fun getItemCount(): Int {
        return items.size
    }

    @CallSuper
    override fun onBindViewHolder(holder: BaseHolder<T>, position: Int) {
        holder.onBind(position, itemCount, items[position])
    }

    override fun onBindViewHolder(holder: BaseHolder<T>, position: Int, payloads: MutableList<Any>) {
        if (payloads.isNotEmpty()) {
            holder.onBind(position, itemCount, items[position], payloads)
        } else {
            super.onBindViewHolder(holder, position, payloads)
        }
    }

    open fun setItems(newItems: List<T>) {
        differ.submitList(newItems)
        items.clear()
        items += newItems
    }

    open fun addItems(newItems: List<T>): List<Int> {
        return newItems.map { addItem(it) }
    }

    open fun addItem(newItem: T): Int {
        items += newItem
        this.notifyItemInserted(items.lastIndex)
        return items.lastIndex
    }

    open fun addItemAt(position: Int, newItem: T) {
        items.add(position, newItem)
        this.notifyItemInserted(position)
    }

    open fun addItemsAt(position: Int, newItems: List<T>) {
        items.addAll(position, newItems)
        this.notifyItemRangeInserted(position, this.itemCount - 1)
    }

    open fun updateItemOn(newItem: T, predicate: (T) -> Boolean) {
        items.forEachIndexed { i, it ->
            if (predicate(it)) {
                this[i] = newItem
                return
            }
        }
    }

    open fun removeAt(position: Int) {
        items.removeAt(position)
        this.notifyItemRemoved(position)
        if (items.lastIndex != 0) this.notifyItemChanged(position)
    }

    open fun clear() {
        val itemsCount = items.size
        items.clear()
        this.notifyItemRangeRemoved(0, itemCount)
    }

    open operator fun get(position: Int): T {
        return items[position]
    }

    open operator fun set(position: Int, newItem: T) {
        items[position] = newItem
        this.notifyItemChanged(position)
    }

}
