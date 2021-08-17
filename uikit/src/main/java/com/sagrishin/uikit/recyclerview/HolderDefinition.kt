package com.sagrishin.uikit.recyclerview

class HolderDefinition<T> {

    var viewType: Int = -1
    lateinit var predicate: HolderPredicate<T>
    lateinit var generator: HolderGenerator<T>

}