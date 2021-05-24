package com.example.school.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.school.entity.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getAllUser(): List<User>

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Query("SELECT * FROM user WHERE user_name LIKE :name LIMIT 1 ")
    fun findByName(name: String): User

    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)
}