package com.sagrishin.common.navutils

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

inline fun <reified T : BackStackEvent> Fragment.getResultLiveData(key: String = T::class.java.name): MutableLiveData<T>? {
    return findNavController().currentBackStackEntry?.run { savedStateHandle.getLiveData(key) }
}

inline fun <reified T> Fragment.setResultToPrevBackStackEntry(key: String = T::class.java.name, result: T) {
    findNavController().previousBackStackEntry?.run { savedStateHandle[key] = result }
}

inline fun <reified T> Fragment.setResultToBackStackEntry(
    @IdRes backStackEntryId: Int,
    key: String = T::class.java.name,
    result: T
) {
    findNavController().findBackStackEntry(backStackEntryId)?.run { savedStateHandle[key] = result }
}


/* TODO: Check, is this overhead is really necessary? Maybe enough observe to getResultLiveData() */
inline fun <reified T : BackStackEvent> Fragment.observeResult(
    lo: LifecycleOwner,
    observer: Observer<T>,
): LiveData<T>? {
    return getResultLiveData<T>()?.also { liveData ->
        liveData.observe(lo, {
            if (it.wasDelivered.compareAndSet(false, true)) {
                observer.onChanged(it)
            }
        })
    }
}
