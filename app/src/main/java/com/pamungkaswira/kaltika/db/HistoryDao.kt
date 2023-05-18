package com.pamungkaswira.kaltika.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HistoryDao {
    @Insert
    fun insert(hasil: HistoryEntity)

    @Query("SELECT * FROM history ORDER BY id DESC")
    fun getHistory(): LiveData<List<HistoryEntity?>>

    @Delete
    fun deleteData(hasil: HistoryEntity?)

    @Query("DELETE FROM history")
    fun clearData()
}