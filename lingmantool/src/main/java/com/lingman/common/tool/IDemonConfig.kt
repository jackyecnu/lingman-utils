package com.lingman.common.tool


//用户DI配置用
interface IDemonConfig {

//    获取Api Root
    fun GetApiRoot():String

    //获取http Header
    fun GetHttpHeader():Map<String,String>


    fun MakeLogout()

    fun GetLoginClass():String

    fun CheckCustomCode(code:Int):Boolean

}