package com.lingman.common.network

data class PolicyUploadBean(
    val accessid: String,
    val dir: String,
    val expire: Int,
    val host: String,
    val policy: String,
    val signature: String
)