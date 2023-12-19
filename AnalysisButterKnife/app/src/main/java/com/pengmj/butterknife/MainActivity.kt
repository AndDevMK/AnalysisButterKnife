package com.pengmj.butterknife

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick

class MainActivity : AppCompatActivity() {

    @JvmField
    @BindView(R.id.tv_text)
    var tvText: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        tvText?.text = "你好哇！"
    }

    @OnClick(R.id.bt_click)
    fun onClick(view: View) {
        if (view.id == R.id.bt_click) {
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }
}