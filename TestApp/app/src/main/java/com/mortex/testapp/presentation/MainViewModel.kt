package com.mortex.testapp.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mortex.testapp.data.UseCaseResult
import com.mortex.testapp.data.cat_repository.CatRepository
import com.mortex.testapp.data.model.Category
import com.mortex.testapp.data.model.ResultModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainViewModel(private val catRepository: CatRepository) : ViewModel(), CoroutineScope {
    // Coroutine's background job
    private val job = Job()

    // Define default thread for Coroutine as Main and add job
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    val showLoading = MutableLiveData<Boolean>()
    val catsList = MutableLiveData<ResultModel>()
    val showError = SingleLiveEvent<String>()

    fun loadCats() {
        // Show progressBar during the operation on the MAIN (default) thread
        showLoading.value = true
        // launch the Coroutine
        launch {
            // Switching from MAIN to IO thread for API operation
            // Update our data list with the new one from API
            val response = withContext(Dispatchers.IO) { catRepository.getCatList() }
            // Hide progressBar once the operation is done on the MAIN (default) thread
            showLoading.value = false
            when (response) {
                is UseCaseResult.Success -> catsList.value = response.data
                is UseCaseResult.Error -> showError.value = response.exception.message
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        // Clear our job when the linked activity is destroyed to avoid memory leaks
        job.cancel()
    }

}