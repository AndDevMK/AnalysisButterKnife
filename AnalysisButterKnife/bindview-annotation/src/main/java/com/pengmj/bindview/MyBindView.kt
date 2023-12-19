package com.pengmj.bindview

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.BINARY)
annotation class MyBindView(val value: Int)