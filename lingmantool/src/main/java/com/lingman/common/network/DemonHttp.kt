package com.lingman.common.network

import android.content.Intent
import android.util.Log
import com.google.gson.JsonParser
import com.lingman.common.tool.DemonApplication
import com.lingman.common.tool.IDemonConfig
import com.lingman.common.utils.DemonToast

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.asRequestBody
import org.koin.core.component.getScopeName
import org.koin.core.context.GlobalContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.IOException
import java.util.*


class DemonHttp {
    companion object {
        /**
         * 加载网络请求，failure 代表code ！= 1. error：代表网络错误 ，final：代表始终要运行的
         */
        suspend fun <T : Any?> load(
            block: suspend () -> ApiResultObj<T>,
            failure: ((ApiResultObj<T>) -> Unit)? = null,
            error: (() -> Unit)? = null,
            final: (() -> Unit)? = null,
        ) = flow {
            //开始请求数据
            val call = block.invoke()

            //将结果复制给baseResp
            final?.invoke()

            val cof: IDemonConfig by lazy { GlobalContext.get().get() }
            if(!cof.CheckCustomCode()) {
                if (call.code == 1) {
                    emit(call.data)
                } else if (call.code == 401) {

                    cof.MakeLogout()
                    try {
                        val clz = Class.forName(cof.GetLoginClass())
                        val intent = Intent(DemonApplication.mContext, clz)
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
                        DemonApplication.mContext.startActivity(intent)
                    } catch (classNotFoundException: ClassNotFoundException) {
                        classNotFoundException.printStackTrace()
                    }
                } else if (failure == null) {
                    DemonToast.showShort(call.message)
                } else {
                    failure.invoke(call)
                }
            }

        }.catch{
            if (error == null) {
                DemonToast.showShort("网络异常")
            } else {
                error.invoke()
            }
            final?.invoke()
        }


        /**
         * 加载网络请求，不需要响应的
         */
        suspend fun <T : Any?> loadNoResponse(
            block: suspend () -> ApiResultObj<T>
        )  {
            try {
                block.invoke()
            }catch (e: Exception) {

            }
        }


        fun uploadOss(policy: PolicyUploadBean, file: File, success: (bd: String) -> Unit) {

            try {
                Log.i("result filename", file.absolutePath)
                val key: String = policy.dir + UUID.randomUUID()
                val fileBody = file.asRequestBody("image/jpg".toMediaType())
                val requestBody = MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("key", key)
                    .addFormDataPart("policy", policy.policy)
                    .addFormDataPart("OSSAccessKeyId", policy.accessid)
                    .addFormDataPart("success_action_status", "200")
                    .addFormDataPart("signature", policy.signature)
                    .addFormDataPart("file", "head_image", fileBody)
                    .build()
                val request: Request = Request.Builder()
                    .url(policy.host)
                    .post(requestBody)
                    .build()

                val httpBuilder = OkHttpClient.Builder()
                val okHttpClient: OkHttpClient = httpBuilder
                    .build()
                okHttpClient.newCall(request).enqueue(object : okhttp3.Callback {
                    override fun onFailure(call: okhttp3.Call, e: IOException) {
                        //  ToastUitl.showShort("网络请求失败")
                        //  callBack.onFailure(e.message)
                        DemonToast.showShort("网络请求失败")
                    }

                    @Throws(IOException::class)
                    override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                        val fileName: String
                        if (policy.host.last() == '/') {
                            fileName = policy.host.toString() + key
                        } else {
                            fileName = policy.host.toString() + "/" + key
                        }
                        success.invoke(fileName)
                    }
                })
            } catch (ex: Exception) {
                // callBack.onFailure(ex.message)
                DemonToast.showShort("上传失败")
                ex.printStackTrace()
            }
        }
    }
}
