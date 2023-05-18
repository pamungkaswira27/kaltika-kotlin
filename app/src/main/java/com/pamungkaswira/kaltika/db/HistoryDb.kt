package com.pamungkaswira.kaltika.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [HistoryEntity::class], version = 2, exportSchema = false)
abstract class HistoryDb : RoomDatabase() {
    abstract val dao: HistoryDao

    companion object {
        @Volatile
        private var INSTANCE: HistoryDb? = null

        fun getInstance(context: Context): HistoryDb {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        HistoryDb::class.java,
                        "history.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}