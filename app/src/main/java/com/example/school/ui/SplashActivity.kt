package com.example.school.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.school.R
import com.example.school.entity.User
import com.example.school.util.DatabaseUtil
import com.tencent.mmkv.MMKV


class SplashActivity : AppCompatActivity() {

    val mmkv = MMKV.defaultMMKV()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_School)
        setContentView(R.layout.activity_splash)

        DatabaseUtil.getDatabase()
        val userDao = DatabaseUtil.db.userDao()
        val allUser = userDao.getAllUser()
        if (allUser.isEmpty()) {
            val user = User(0, "admin", 0, "123456")
            userDao.insertAll(user)
        }

        Handler(Looper.getMainLooper()).postDelayed({
            if (mmkv.decodeString("user") == null) {
                startActivity(
                    Intent(
                        this@SplashActivity,
                        Login::class.java
                    )
                )
                finish()
            } else {
                startActivity(
                    Intent(
                        this@SplashActivity,
                        MainActivity::class.java
                    )
                )
                finish()
            }
        }, 500)

    }

    override fun onDestroy() {
        super.onDestroy()
        DatabaseUtil.closeDd()
    }

}