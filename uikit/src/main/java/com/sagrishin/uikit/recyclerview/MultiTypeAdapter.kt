package com.sagrishin.uikit.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil

open class MultiTypeAdapter<T> constructor(
    override val items: MutableList<T> = mutableListOf(),
    override val diffUtilCallback: DiffUtil.ItemCallback<T>
) : BaseRecyclerViewAdapter<T>() {

    private val viewTypes: MutableMap<HolderPredicate<T>, Int> = mutableMapOf()
    private val holderGenerators: MutableMap<Int, HolderGenerator<T>> = mutableMapOf()

    constructor(
        items: List<T> = listOf(),
        diffUtilCallback: DiffUtil.ItemCallback<T>,
        initializer: Initializer<T>,
    ) : this(items.toMutableList(), diffUtilCallback) {
        apply(initializer)
    }

    override fun getItemViewType(position: Int): Int {
        try {
            return viewTypes.filterKeys { it(this.items[position]) }.values.first()
        } catch (e: Throwable) {
            /// this try-catch added for possibility catch an exception in debug mode
            /// here shouldn't  be some catch logic, cuz exception here in most cases
            /// means, that there was an error, when list was developed, so it just
            /// rethrown
            throw e
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<T> {
        val baseHolder = holderGenerators[viewType]!!(parent) as BaseHolder<T>
        baseHolder.onCreate()
        return baseHolder
    }

    fun holder(block: HolderDefinition<T>.() -> Unit) {
        val hd = HolderDefinition<T>().apply(block)
        viewTypes[hd.predicate] = hd.viewType
        holderGenerators[hd.viewType] = hd.generator
    }

}
