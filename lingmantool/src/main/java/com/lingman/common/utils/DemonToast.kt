package com.lingman.common.utils

import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.lingman.common.tool.DemonApplication


/**
 * Toast统一管理类
 */
class DemonToast {
    companion object {
        /**
         * 短时间显示Toast
         *
         * @param message
         */
        fun showShort(message: CharSequence) {


            Handler(Looper.getMainLooper()).post{
                Toast.makeText(DemonApplication.mContext,message,Toast.LENGTH_SHORT).show()
            }
        }

        /**
         * 短时间显示Toast
         *
         * @param strResId
         */
        fun showShort(strResId: Int) {
            Handler(Looper.getMainLooper()).post{
                Toast.makeText(DemonApplication.mContext,DemonApplication.mContext.getResources().getText(strResId),Toast.LENGTH_SHORT).show()
            }

        }

        /**
         * 长时间显示Toast
         *
         * @param message
         */
        fun showLong(message: CharSequence) {

            Handler(Looper.getMainLooper()).post{
                Toast.makeText(DemonApplication.mContext,message,Toast.LENGTH_LONG).show()
            }
        }

        /**
         * 长时间显示Toast
         *
         * @param strResId
         */
        fun showLong(strResId: Int) {
            Handler(Looper.getMainLooper()).post{
                Toast.makeText(DemonApplication.mContext,DemonApplication.mContext.getResources().getText(strResId),Toast.LENGTH_LONG).show()
            }
        }


    }

}
