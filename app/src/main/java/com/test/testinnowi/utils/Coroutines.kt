package com.test.testinnowi.utils

import androidx.lifecycle.viewModelScope
import com.test.testinnowi.view_models.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object Coroutines {
    fun onMain(work: suspend (() -> Unit)) {
        CoroutineScope(Dispatchers.Main).launch {
            work()
        }
    }

    fun onIO(work: suspend (() -> Unit)) {
        CoroutineScope(Dispatchers.IO).launch {
            work()
        }
    }

    fun BaseViewModel.run(work: suspend (() -> Unit)) {
        viewModelScope.launch(exceptionHandler) {
            work()
        }
    }
}