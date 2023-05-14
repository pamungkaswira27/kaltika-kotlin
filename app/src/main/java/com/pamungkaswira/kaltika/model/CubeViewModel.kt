package com.pamungkaswira.kaltika.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CubeViewModel : ViewModel() {
    private val hasilLuasPermukaan = MutableLiveData<Double?>()
    private val hasilVolume = MutableLiveData<Double?>()

    fun hitungLuasPermukaan(rusuk: Double) {
        hasilLuasPermukaan.value = 6 * rusuk * rusuk
    }

    fun hitungVolume(rusuk: Double) {
        hasilVolume.value = rusuk * rusuk * rusuk
    }

    fun getHasilLuasPermukaan(): LiveData<Double?> = hasilLuasPermukaan

    fun getHasilVolume(): LiveData<Double?> = hasilVolume
}