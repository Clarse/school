package com.example.school.util

import androidx.room.Room
import com.example.school.App
import com.example.school.database.AppDatabase

object DatabaseUtil {

    lateinit var db: AppDatabase

    fun getDatabase() {
        db = Room.databaseBuilder(
            App.getInstance().applicationContext,
            AppDatabase::class.java,
            "appDatabase"
        ).allowMainThreadQueries().build()
    }

    fun closeDd() {
        if (::db.isInitialized) {
            db.close()
        }
    }

}