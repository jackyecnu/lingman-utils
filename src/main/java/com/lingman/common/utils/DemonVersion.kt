package com.lingman.common.utils

import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import com.lingman.common.tool.DemonApplication


class DemonVersion {
    companion object {
        /**
         * 获取当前本地apk的版本
         *
         * @param mContext
         * @return
         */
        fun getVersionCode(): Int {
            try {
                val pInfo: PackageInfo =  DemonApplication.mContext.getPackageManager().getPackageInfo(DemonApplication.mContext.getPackageName(), 0)
                val version = pInfo.versionCode
                return  version
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
                return  0
            }
        }

        /**
         * 获取版本号名称
         *
         * @param context 上下文
         * @return
         */
        fun getVersionName(): String {
            try {
                val pInfo: PackageInfo =  DemonApplication.mContext.getPackageManager().getPackageInfo(DemonApplication.mContext.getPackageName(), 0)
                val version = pInfo.versionName
                return  version
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
                return  ""
            }
        }
    }


}