package com.pengmj.bindview

import androidx.annotation.UiThread

/**
 * @author MinKin
 * @date 2022/10/15
 * @desc
 */
interface IBindView<T> {
    @UiThread
    fun bind(target:T)
}