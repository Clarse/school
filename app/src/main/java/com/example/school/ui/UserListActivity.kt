package com.example.school.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.school.R
import com.example.school.adapter.UserListAdapter
import com.example.school.dao.UserDao
import com.example.school.entity.User
import com.example.school.util.DatabaseUtil
import com.example.school.util.Extensions.showAlertDialog
import kotlinx.android.synthetic.main.activity_user_list.*

class UserListActivity : AppCompatActivity(), UserListAdapter.OnItemClickListener {

    private lateinit var list: List<User>
    private lateinit var adapter: UserListAdapter
    private lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)
        DatabaseUtil.getDatabase()
        userDao = DatabaseUtil.db.userDao()
        list = userDao.getAllUser()
        adapter = UserListAdapter()
        adapter.setData(list)
        adapter.setOnItemClickListener(this)
        initView()
    }

    private fun initView() {
        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.adapter = adapter
        toolbar4.setNavigationOnClickListener {
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        DatabaseUtil.closeDd()
    }

    override fun onItemClick(position: Int) {
        showAlertDialog(
            "确定", "提示信息", "是否删除此账号？"
        ) {
            if (list[position].userType == 0) {
                Toast.makeText(this, "不能删除教师账号", Toast.LENGTH_SHORT).show()
            } else {
                userDao.delete(list[position])
                Toast.makeText(this, "删除账号成功！", Toast.LENGTH_SHORT).show()
                list = userDao.getAllUser()
                adapter.setData(list)
            }
        }
    }

}