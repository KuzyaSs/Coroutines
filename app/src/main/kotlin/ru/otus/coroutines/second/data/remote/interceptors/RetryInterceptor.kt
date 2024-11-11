package ru.otus.coroutines.second.data.remote.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.net.SocketTimeoutException

private const val MAX_ATTEMPTS = 3

internal class RetryInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        var response: Response? = null
        var attempt = 0

        while (attempt < MAX_ATTEMPTS && response?.isSuccessful != true) {
            response?.close()
            try {
                response = chain.proceed(request)
            } catch (_: SocketTimeoutException) {
            }
            attempt++
        }

        return response ?: throw IOException("Exceeded max attempts")
    }
}
