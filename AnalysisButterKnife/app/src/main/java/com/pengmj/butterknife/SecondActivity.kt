package com.pengmj.butterknife

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.pengmj.bindview.MyBindView
import com.pengmj.butterknife.third.ThirdActivity

class SecondActivity : AppCompatActivity() {

    @JvmField  // 一定要加这个注解，否则访问不到
    @MyBindView(R.id.tv_text)
    var tvText: TextView? = null

    @JvmField
    @MyBindView(R.id.bt_click)
    var btClick: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        BindView.bind(this)
        tvText?.text = SecondActivity::class.java.simpleName
        btClick?.setOnClickListener {
            startActivity(Intent(this, ThirdActivity::class.java))
        }
    }
}