package com.example.school.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.school.dao.UserDao
import com.example.school.entity.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}