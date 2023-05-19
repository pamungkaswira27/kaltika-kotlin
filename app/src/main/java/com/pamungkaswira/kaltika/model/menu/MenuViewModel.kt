package com.pamungkaswira.kaltika.model.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pamungkaswira.kaltika.R
import com.pamungkaswira.kaltika.ui.menu.MenuData

class MenuViewModel : ViewModel() {
    private val menuData = MutableLiveData<List<MenuData>>()

    init {
        menuData.value = initData()
    }

    private fun initData() : List<MenuData> {
        return listOf(
            MenuData(R.drawable.cube, "Kubus", "Bangun Ruang"),
            MenuData(R.drawable.cuboid, "Balok", "Bangun Ruang"),
            MenuData(R.drawable.none, "Aritmatika", "Barisan & Deret"),
            MenuData(R.drawable.none, "Geometri", "Barisan & Deret"),
        )
    }

    fun getData(): LiveData<List<MenuData>> = menuData
}