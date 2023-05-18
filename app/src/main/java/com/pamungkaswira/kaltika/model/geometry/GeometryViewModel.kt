package com.pamungkaswira.kaltika.model.geometry

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pamungkaswira.kaltika.db.HistoryDao
import com.pamungkaswira.kaltika.db.HistoryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.pow

class GeometryViewModel(private val db: HistoryDao) : ViewModel() {
    private val hasilBarisan = MutableLiveData<Double?>()
    private val hasilDeret = MutableLiveData<Double?>()
    private var kategori = "Barisan & Deret Geometri"

    fun hitungBarisan(sukuPertama: Double, rasio: Double, sukuN: Double) {
        hasilBarisan.value = sukuPertama * rasio.pow(sukuN - 1)

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

    fun hitungDeret(sukuPertama: Double, rasio: Double, sukuN: Double) {
        if (rasio > 1) {
            hasilDeret.value = sukuPertama * (rasio.pow(sukuN) - 1) / (rasio - 1)
        }
        else if (rasio < 1) {
            hasilDeret.value = sukuPertama * (1 - rasio.pow(sukuN)) / (1 - rasio)
        }

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