package com.pamungkaswira.kaltika.model.arithmetic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pamungkaswira.kaltika.db.HistoryDao
import com.pamungkaswira.kaltika.db.HistoryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ArithmeticViewModel(private val db: HistoryDao) : ViewModel() {
    private val hasilBarisan = MutableLiveData<Double?>()
    private val hasilDeret = MutableLiveData<Double?>()
    private var kategori = "Barisan & Deret Aritmatika"

    fun hitungBarisan(sukuPertama: Double, beda: Double, sukuN: Double) {
        hasilBarisan.value = sukuPertama + (sukuN - 1) * beda

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val dataHistory = HistoryEntity(
                    kategori = kategori,
                    hasil = hasilBarisan.value!!
                )

                db.insert(dataHistory)
            }
        }
    }

    fun hitungDeret(sukuPertama: Double, beda: Double, sukuN: Double) {
        hasilDeret.value = sukuN / 2 * (2 * sukuPertama + (sukuN - 1) * beda)

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val dataHistory = HistoryEntity(
                    kategori = kategori,
                    hasil = hasilDeret.value!!
                )

                db.insert(dataHistory)
            }
        }
    }

    fun getHasilBarisan(): LiveData<Double?> = hasilBarisan

    fun getHasilDeret(): LiveData<Double?> = hasilDeret
}