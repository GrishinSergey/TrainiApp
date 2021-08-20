package com.sagrishin.common.viewmodels

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sagrishin.common.BuildConfig
import com.sagrishin.common.livedatas.ActionLiveData
import com.sagrishin.common.utils.refresh
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel: ViewModel() {

    val errorsLiveData: LiveData<Throwable?>
        get() = _errorsLiveData
    protected val _errorsLiveData = ActionLiveData<Throwable>()

    @SuppressLint("LogNotTimber")
    val commonErrorsHandler = CoroutineExceptionHandler { _, throwable ->
        if (BuildConfig.DEBUG) {
            Log.e(this::class.java.name, throwable.message, throwable)
        }
        _errorsLiveData.refresh(throwable)
    }

    protected fun launch(
        context: CoroutineContext = Dispatchers.Main,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ) {
        viewModelScope.launch(context + commonErrorsHandler, start, block)
    }

}
