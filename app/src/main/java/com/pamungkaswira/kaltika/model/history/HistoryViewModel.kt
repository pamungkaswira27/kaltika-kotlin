package com.pamungkaswira.kaltika.model.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pamungkaswira.kaltika.db.HistoryDao
import com.pamungkaswira.kaltika.db.HistoryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HistoryViewModel(private val db: HistoryDao) : ViewModel() {
    val data = db.getHistory()

    fun deleteData(dataHistory: HistoryEntity) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            db.deleteData(dataHistory)
        }
    }

    fun clearData() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            db.clearData()
        }
    }
}