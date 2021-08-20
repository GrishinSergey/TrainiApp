package com.sagrishin.uikit.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.AdapterListUpdateCallback
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil

open class SingleTypeAdapter<T> constructor(
    override val items: MutableList<T> = mutableListOf(),
    final override var diffUtilCallback: DiffUtil.ItemCallback<T>,
) : BaseRecyclerViewAdapter<T>() {

    override val differ: AsyncListDiffer<T> = AsyncListDiffer<T>(
        AdapterListUpdateCallback(this),
        AsyncDifferConfig.Builder(diffUtilCallback).build()
    )

    private lateinit var holderGenerator: HolderGenerator<T>

    @Suppress("UNCHECKED_CAST")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<T> {
        val baseHolder = holderGenerator(parent) as BaseHolder<T>
        baseHolder.onCreate()
        return baseHolder
    }

    open fun holder(generator: HolderGenerator<T>) {
        this.holderGenerator = generator
    }

}
