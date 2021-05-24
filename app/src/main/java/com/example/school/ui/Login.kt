package com.example.school.ui

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.school.R
import com.example.school.dao.UserDao
import com.example.school.util.DatabaseUtil
import com.tencent.mmkv.MMKV
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {

    val mmkv = MMKV.defaultMMKV()
    lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        DatabaseUtil.getDatabase()
        userDao = DatabaseUtil.db.userDao()

    }

    fun login(view: View) {
        when {
            TextUtils.isEmpty(userName.text) -> {
                Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show()
            }
            TextUtils.isEmpty(password.text) -> {
                Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show()
            }
            else -> {
                val findByName = userDao.findByName(userName.text.toString())
                when {
                    findByName == null -> {
                        Toast.makeText(this, "没有该用户", Toast.LENGTH_SHORT).show()
                    }
                    findByName.userPassword != password.text.toString() -> {
                        Toast.makeText(this, "密码错误", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        mmkv.encode("user", userName.text.toString())
                        startActivity(Intent(this, MainActivity::class.java))
                    }
                }
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        DatabaseUtil.closeDd()
    }

}