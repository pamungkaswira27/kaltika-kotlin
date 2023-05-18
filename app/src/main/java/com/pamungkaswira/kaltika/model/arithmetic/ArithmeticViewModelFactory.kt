package com.pamungkaswira.kaltika.model.arithmetic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pamungkaswira.kaltika.db.HistoryDao

class ArithmeticViewModelFactory(private val db: HistoryDao) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArithmeticViewModel::class.java)) {
            return ArithmeticViewModel(db) as T
        }

        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}