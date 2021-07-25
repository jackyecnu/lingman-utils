package com.lingman.common.utils

import java.util.regex.Pattern

class DemonRegex {
    companion object {
        fun isPhoneNum(phone: String): Boolean {
            val compile = Pattern.compile("^(13|14|15|16|17|18|19)\\d{9}$")
            val matcher = compile.matcher(phone)
            return matcher.matches()
        }
    }
}