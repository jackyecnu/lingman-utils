package com.lingman.common.network

data class ApiResultObj<T> (
    val code:Int,
    val data:T,
    val message:String
)

