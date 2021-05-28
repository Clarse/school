package com.example.school.ui

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.school.R
import com.tencent.mmkv.MMKV
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val mmkv = MMKV.defaultMMKV()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {
        userName.text = mmkv.decodeString("user_name")
        toolbar.setNavigationOnClickListener {
            finish()
        }
        search_user.setOnClickListener {
            startActivity(Intent(this, UserListActivity::class.java))
        }
        addUser.setOnClickListener {
            startActivity(Intent(this, UserAddActivity::class.java))
        }
        sign.setOnClickListener {
            startActivity(Intent(this, SignActivity::class.java))
        }
        login_out.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("提示信息")
                .setMessage("是否退出登录")
                .setPositiveButton(
                    "确定"
                ) { _, _ ->
                    mmkv.clearAll()
                    val intent = Intent(applicationContext, LoginActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()
                }
            val alertDialog = builder.create()
            alertDialog.show()
        }
    }

}