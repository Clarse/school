package com.example.school.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) var uid: Int,
    @ColumnInfo(name = "user_type") var userType: Int,
    @ColumnInfo(name = "user_number") var userNumber: String,
    @ColumnInfo(name = "user_name") var userName: String,
    @ColumnInfo(name = "user_password") var userPassword: String
)
