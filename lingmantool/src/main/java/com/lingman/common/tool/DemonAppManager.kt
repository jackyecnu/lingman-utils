package com.lingman.common.tool

import android.app.Activity
import java.util.*


class DemonAppManager private constructor(){

    // https://github.com/teaphy/Kotlin-Dagger-2-Retrofit-Android-Architecture-Components/blob/master/app/src/main/java/com/example/administrator/archdemo/base/AppManager.kt

    //管理所有activity
    var mActivityList: MutableList<Activity>? = null

    /**
     * 将在前台的activity保存
     * @param currentActivity
     */
    var currentActivity: Activity? = null

    companion object {
        val instance:DemonAppManager by lazy { DemonAppManager() }
    }

    /**
     * 添加Activity到集合
     */
    fun addActivity(activity: Activity) {
        if (mActivityList == null) {
            mActivityList = LinkedList<Activity>()
        }
        synchronized(DemonAppManager::class.java) {
            if (!mActivityList!!.contains(activity)) {
                mActivityList!!.add(activity)
            }
        }
    }

    /**
     * 返回一个存储所有未销毁的activity的集合
     * @return
     */
    fun getActivityList(): MutableList<Activity>? {

        if (mActivityList == null) {
            mActivityList = mutableListOf()
        }
        return mActivityList
    }


    /**
     * 删除集合里的指定activity
     * @param activity
     */
    fun removeActivity(activity: Activity) {
        if (mActivityList == null) {
            return
        }
        synchronized(DemonAppManager::class.java) {
            if (mActivityList!!.contains(activity)) {
                mActivityList!!.remove(activity)
            }
        }
    }


    /**
     * 关闭指定activity
     * @param activityClass
     */
    fun killActivity(activityClass: Class<*>) {
        if (mActivityList == null) {
            return
        }
        mActivityList!!
            .filter { it.javaClass == activityClass }
            .forEach { it.finish() }
    }




}
