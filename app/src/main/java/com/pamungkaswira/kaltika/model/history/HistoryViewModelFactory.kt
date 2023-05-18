package com.pamungkaswira.kaltika.model.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pamungkaswira.kaltika.db.HistoryDao

class HistoryViewModelFactory(private val db: HistoryDao) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HistoryViewModel::class.java)) {
            return HistoryViewModel(db) as T
        }

        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}