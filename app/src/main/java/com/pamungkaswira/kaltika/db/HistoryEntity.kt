package com.pamungkaswira.kaltika.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var kategori: String,
    val hasil: Double
)