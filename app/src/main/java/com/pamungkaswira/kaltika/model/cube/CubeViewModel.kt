package com.pamungkaswira.kaltika.model.cube

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pamungkaswira.kaltika.R
import com.pamungkaswira.kaltika.db.HistoryDao
import com.pamungkaswira.kaltika.db.HistoryDb
import com.pamungkaswira.kaltika.db.HistoryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CubeViewModel(private val db: HistoryDao) : ViewModel() {
    private val hasilLuasPermukaan = MutableLiveData<Double?>()
    private val hasilVolume = MutableLiveData<Double?>()
    private var kategori = "Kubus"

    fun hitungLuasPermukaan(rusuk: Double) {
        hasilLuasPermukaan.value = 6 * rusuk * rusuk

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val dataHistory = HistoryEntity(
                    kategori = kategori,
                    hasil = hasilLuasPermukaan.value!!
                )

                db.insert(dataHistory)
            }
        }
    }

    fun hitungVolume(rusuk: Double) {
        hasilVolume.value = rusuk * rusuk * rusuk

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val dataHistory = HistoryEntity(
                    kategori = kategori,
                    hasil = hasilVolume.value!!
                )

                db.insert(dataHistory)
            }
        }
    }

    fun getHasilLuasPermukaan(): LiveData<Double?> = hasilLuasPermukaan

    fun getHasilVolume(): LiveData<Double?> = hasilVolume
}