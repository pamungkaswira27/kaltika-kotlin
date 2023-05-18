package com.pamungkaswira.kaltika.model.cuboid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pamungkaswira.kaltika.db.HistoryDao
import com.pamungkaswira.kaltika.db.HistoryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CuboidViewModel(private val db: HistoryDao) : ViewModel() {
    private val hasilLuasPermukaan = MutableLiveData<Double?>()
    private val hasilVolume = MutableLiveData<Double?>()
    private var kategori = "Balok"

    fun hitungLuasPermukaan(panjang: Double, lebar: Double, tinggi: Double) {
        hasilLuasPermukaan.value = 2 * ((panjang * lebar) + (panjang * tinggi) + (lebar * tinggi))

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

    fun hitungVolume(panjang: Double, lebar: Double, tinggi: Double) {
        hasilVolume.value = panjang * lebar * tinggi

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