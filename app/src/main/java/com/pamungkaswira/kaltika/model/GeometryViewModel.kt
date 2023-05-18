package com.pamungkaswira.kaltika.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.math.pow

class GeometryViewModel : ViewModel() {
    private val hasilBarisan = MutableLiveData<Double?>()
    private val hasilDeret = MutableLiveData<Double?>()

    fun hitungBarisan(sukuPertama: Double, rasio: Double, sukuN: Double) {
        hasilBarisan.value = sukuPertama * rasio.pow(sukuN - 1)
    }

    fun hitungDeret(sukuPertama: Double, rasio: Double, sukuN: Double) {
        if (rasio > 1) {
            hasilDeret.value = sukuPertama * (rasio.pow(sukuN) - 1) / (rasio - 1)
        }
        else if (rasio < 1) {
            hasilDeret.value = sukuPertama * (1 - rasio.pow(sukuN)) / (1 - rasio)
        }
    }

    fun getHasilBarisan(): LiveData<Double?> = hasilBarisan

    fun getHasilDeret(): LiveData<Double?> = hasilDeret
}