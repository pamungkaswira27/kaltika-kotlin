package com.pamungkaswira.kaltika.model.geometry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pamungkaswira.kaltika.db.HistoryDao

class GeometryViewModelFactory(private val db: HistoryDao) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GeometryViewModel::class.java)) {
            return GeometryViewModel(db) as T
        }

        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}