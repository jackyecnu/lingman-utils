package com.lingman.taohupai.ui.common

data class ApiResultObj<T> (
    val code:Int,
    val data:T,
    val message:String
)

