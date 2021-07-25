package com.lingman.common.tool;
import android.content.Context

class DemonApplication {
    companion object {
        lateinit var mContext: Context
        fun init(context: Context) {
            mContext = context
        }
    }
}