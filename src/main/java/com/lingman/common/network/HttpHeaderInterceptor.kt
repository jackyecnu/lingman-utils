package com.lingman.common.network

import com.lingman.common.tool.IDemonConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import org.koin.core.context.GlobalContext
import java.io.IOException

class HttpHeaderInterceptor : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {
        val cof: IDemonConfig by lazy { GlobalContext.get().get() }
        val builder = chain.request().newBuilder()
            val headerKeySet: Set<String> = cof.GetHttpHeader().keys
            for (key in headerKeySet) {
                val value: String? = cof.GetHttpHeader().get(key)
                value?.let { a ->
                    builder.addHeader(key, a)
                }
            }
        val requst: Request = builder.build()
        return chain.proceed(requst)
    }



}
