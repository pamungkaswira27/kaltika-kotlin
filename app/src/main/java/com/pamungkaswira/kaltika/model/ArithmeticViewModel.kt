package com.pamungkaswira.kaltika.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ArithmeticViewModel : ViewModel() {
    private val hasilBarisan = MutableLiveData<Double?>()
    private val hasilDeret = MutableLiveData<Double?>()

    fun hitungBarisan(sukuPertama: Double, beda: Double, sukuN: Double) {
        hasilBarisan.value = sukuPertama + (sukuN - 1) * beda
    }

    fun hitungDeret(sukuPertama: Double, beda: Double, sukuN: Double) {
        hasilDeret.value = sukuN / 2 * (2 * sukuPertama + (sukuN - 1) * beda)
    }

    fun getHasilBarisan(): LiveData<Double?> = hasilBarisan

    fun getHasilDeret(): LiveData<Double?> = hasilDeret
}