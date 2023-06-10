package com.pamungkaswira.kaltika.model.menu

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.pamungkaswira.kaltika.network.ApiStatus
import com.pamungkaswira.kaltika.network.FormulaApi
import com.pamungkaswira.kaltika.network.ReminderWorker
import com.pamungkaswira.kaltika.ui.menu.Formula
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class MenuViewModel : ViewModel() {
    private val formula = MutableLiveData<List<Formula>>()
    private val status = MutableLiveData<ApiStatus>()

    init {
        retrieveData()
    }

    private fun retrieveData() {
        viewModelScope.launch(Dispatchers.IO) {
            status.postValue(ApiStatus.LOADING)
            try {
                formula.postValue(FormulaApi.service.getFormula())
                status.postValue(ApiStatus.SUCCESS)
            } catch (e: Exception) {
                Log.w("MainViewModel", "Failure: ${e.message}")
                status.postValue(ApiStatus.FAILED)
            }
        }
    }

    fun getData(): LiveData<List<Formula>> = formula

    fun getStatus(): LiveData<ApiStatus> = status

    fun scheduleUpdater(app: Application) {
        val request = OneTimeWorkRequestBuilder<ReminderWorker>()
            .setInitialDelay(1, TimeUnit.MINUTES)
            .build()

        WorkManager.getInstance(app).enqueueUniqueWork(
            ReminderWorker.WORK_NAME,
            ExistingWorkPolicy.REPLACE,
            request
        )
    }
}