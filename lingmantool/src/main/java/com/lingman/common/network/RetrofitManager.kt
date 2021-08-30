package com.lingman.common.network

import android.util.Log
import com.lingman.common.network.config.LocalCookieJar
import com.lingman.common.tool.IDemonConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.context.GlobalContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val TAG = "RetrofitManager"

//object RetrofitManager {
//
//    private val mOkClient = OkHttpClient.Builder()
//        .callTimeout(10, TimeUnit.SECONDS)
//        .connectTimeout(10, TimeUnit.SECONDS)
//        .readTimeout(10, TimeUnit.SECONDS)
//        .writeTimeout(10, TimeUnit.SECONDS)
//        .retryOnConnectionFailure(true)
//        .followRedirects(false)
//        .cookieJar(LocalCookieJar())
//        .addInterceptor(HttpHeaderInterceptor())
//        .addInterceptor(HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
//            override fun log(message: String) {
//                Log.d(TAG, "log: $message")
//            }
//
//        }).setLevel(HttpLoggingInterceptor.Level.BODY)).build()
//
//    private var mRetrofit: Retrofit? = null
//
//
//    fun initRetrofit(): RetrofitManager {
//        val repo: IDemonConfig by lazy { GlobalContext.get().get() }
//        mRetrofit = Retrofit.Builder()
//            .baseUrl(repo.GetApiRoot())
//            .client(mOkClient)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//        return this
//    }
//
//    fun <T> getService(serviceClass: Class<T>): T {
//        if (mRetrofit == null) {
//            throw UninitializedPropertyAccessException("Retrofit必须初始化")
//        } else {
//            return mRetrofit!!.create(serviceClass)
//        }
//    }
//}

class RetrofitClient{

    companion object
    {

        fun getRetrofitObject(): Retrofit {
            val mOkClient = OkHttpClient.Builder()
                .callTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .followRedirects(false)
                .cookieJar(LocalCookieJar())
                .addInterceptor(HttpHeaderInterceptor())
                .addInterceptor(HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
                    override fun log(message: String) {
                        Log.d(TAG, "log: $message")
                    }

                }).setLevel(HttpLoggingInterceptor.Level.BODY)).build()

            val repo: IDemonConfig by lazy { GlobalContext.get().get() }


            return Retrofit.Builder()
                .baseUrl(repo.GetApiRoot())
                .client(mOkClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }


        fun getRetrofitObjectNoRoot(): Retrofit {
            val mOkClient = OkHttpClient.Builder()
                .callTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .followRedirects(false)
                .cookieJar(LocalCookieJar())
                .addInterceptor(HttpHeaderInterceptor())
                .addInterceptor(HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
                    override fun log(message: String) {
                        Log.d(TAG, "log: $message")
                    }

                }).setLevel(HttpLoggingInterceptor.Level.BODY)).build()

            val repo: IDemonConfig by lazy { GlobalContext.get().get() }


            return Retrofit.Builder()
                .client(mOkClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }



    }
}



