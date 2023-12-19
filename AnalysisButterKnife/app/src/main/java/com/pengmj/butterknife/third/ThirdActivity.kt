package com.pengmj.butterknife.third

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.pengmj.bindview.MyBindView
import com.pengmj.butterknife.BindView
import com.pengmj.butterknife.R

/**
 * 专门给ThirdActivity弄个不同包下，验证注解处理器获取包名是否成功
 */
class ThirdActivity : AppCompatActivity() {

    @JvmField
    @MyBindView(R.id.tv_text)
    var tvText: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
        BindView.bind(this)
        tvText?.text = ThirdActivity::class.java.simpleName
    }
}