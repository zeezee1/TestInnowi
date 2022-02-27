package com.test.testinnowi.view_models

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.testinnowi.MyApplication
import com.test.testinnowi.models.ErrorModel
import com.test.testinnowi.rest_api.RestClientManager
import kotlinx.coroutines.CoroutineExceptionHandler
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class BaseViewModel(context: Context) : ViewModel() {

    protected val restService = RestClientManager.getInstance(context).restService

    abstract fun handleError()

    private var mError = MutableLiveData<ErrorModel>()
    var error: LiveData<ErrorModel> = mError

    val exceptionHandler: CoroutineExceptionHandler
        get() = CoroutineExceptionHandler { _, throwable ->
            val message = throwable.message
            val error = if (message == null) {
                ErrorModel(500, "Internal server error")
            } else if (throwable is IOException || throwable is UnknownHostException) {
                ErrorModel(404, "No Internet!")
            } else if (throwable is SocketTimeoutException) {
                ErrorModel(405, "Connection timeout")
            } else {
                ErrorModel(401, "Something went wrong")

            }
            mError.postValue(error)
            handleError()
        }

    private var mLoadingState = MutableLiveData<LoadingState>()
    var loadingState: LiveData<LoadingState> = mLoadingState

    fun loading(loadingState: LoadingState) {
        mLoadingState.postValue(loadingState)
    }

    enum class LoadingState {
        SHOW_PROGRESS,
        HIDE_PROGRESS
    }
}