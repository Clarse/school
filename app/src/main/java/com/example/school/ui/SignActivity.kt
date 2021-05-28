package com.example.school.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.qrcode.Constant
import com.example.qrcode.ScannerActivity
import com.example.school.R
import com.example.school.util.Extensions.showAlertDialog
import kotlinx.android.synthetic.main.activity_sign.*

class SignActivity : AppCompatActivity() {

    private var hasRefuse = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)

        checkPermissions()
        initClick()
    }

    private fun initClick() {
        toolbar_sign.setNavigationOnClickListener {
            finish()
        }
        scan.setOnClickListener {
            val intent = Intent(this, ScannerActivity::class.java)
            intent.putExtra(Constant.EXTRA_IS_ENABLE_SCAN_FROM_PIC, true)
            intent.putExtra(Constant.EXTRA_SCANNER_FRAME_WIDTH, 800)
            intent.putExtra(Constant.EXTRA_SCANNER_FRAME_HEIGHT, 800)
            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (data != null) {
                val content = data.getStringExtra(Constant.EXTRA_RESULT_CONTENT)
                scan_result.text = content
            }
        }
    }

    private fun checkPermissions() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            hasRefuse = ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.CAMERA
            )
            hasRefuse = ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            if (hasRefuse) {
                showAlertDialog("确定", "重要提示", "请去设置里开启权限！") {
                    val intent = Intent("android.settings.APPLICATION_DETAILS_SETTINGS")
                    intent.data = Uri.fromParts("package", packageName, null);
                    startActivity(intent);
                    finish()
                }
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    1
                )
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            var isAllGranted = true
            grantResults.forEach {
                if (it != PackageManager.PERMISSION_GRANTED) {
                    isAllGranted = false
                    return@forEach
                }
            }
            if (!isAllGranted) {
                Toast.makeText(this, "请申请需要的权限！", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

}