package com.pamungkaswira.kaltika.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CuboidViewModel : ViewModel() {
    private val hasilLuasPermukaan = MutableLiveData<Double?>()
    private val hasilVolume = MutableLiveData<Double?>()

    fun hitungLuasPermukaan(panjang: Double, lebar: Double, tinggi: Double) {
        hasilLuasPermukaan.value = 2 * ((panjang * lebar) + (panjang * tinggi) + (lebar * tinggi))
    }

    fun hitungVolume(panjang: Double, lebar: Double, tinggi: Double) {
        hasilVolume.value = panjang * lebar * tinggi
    }

    fun getHasilLuasPermukaan(): LiveData<Double?> = hasilLuasPermukaan

    fun getHasilVolume(): LiveData<Double?> = hasilVolume
}