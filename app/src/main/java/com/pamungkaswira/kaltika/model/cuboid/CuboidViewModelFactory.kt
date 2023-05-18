package com.pamungkaswira.kaltika.model.cuboid

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pamungkaswira.kaltika.db.HistoryDao

class CuboidViewModelFactory(private val db: HistoryDao) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CuboidViewModel::class.java)) {
            return CuboidViewModel(db) as T
        }

        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}