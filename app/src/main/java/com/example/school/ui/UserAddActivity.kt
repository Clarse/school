package com.example.school.ui

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.school.R
import com.example.school.dao.UserDao
import com.example.school.entity.User
import com.example.school.util.DatabaseUtil
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_user_add.*

class UserAddActivity : AppCompatActivity() {

    private lateinit var userDao: UserDao
    private lateinit var allUser: List<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_add)
        DatabaseUtil.getDatabase()
        userDao = DatabaseUtil.db.userDao()
        allUser = userDao.getAllUser()
        initView()
    }

    private fun initView() {
        toolbar3.setNavigationOnClickListener {
            finish()
        }
        register.setOnClickListener {
            when {
                TextUtils.isEmpty(et_number.text) -> {
                    Toast.makeText(this, "请输入学号", Toast.LENGTH_SHORT).show()
                }
                TextUtils.isEmpty(et_name.text) -> {
                    Toast.makeText(this, "请输入姓名", Toast.LENGTH_SHORT).show()
                }
                TextUtils.isEmpty(et_password.text) -> {
                    Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show()
                }
            }
            allUser.forEach {
                if (it.userNumber == et_number.text.toString()) {
                    Toast.makeText(this, "用户已存在", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }
            val user = User(
                0,
                1,
                et_number.text.toString(),
                et_name.text.toString(),
                et_password.text.toString()
            )
            userDao.insertAll(user)
            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        DatabaseUtil.closeDd()
    }

}