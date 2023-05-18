package com.pamungkaswira.kaltika.model.cube

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pamungkaswira.kaltika.db.HistoryDao

class CubeViewModelFactory(private val db: HistoryDao) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CubeViewModel::class.java)) {
            return CubeViewModel(db) as T
        }

        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}