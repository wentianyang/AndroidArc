package com.android.network.exception

import com.google.gson.JsonParseException
import org.apache.http.conn.ConnectTimeoutException
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.text.ParseException
import javax.net.ssl.SSLHandshakeException

class ExceptionHandler {

    companion object {
        // 客户端错误
        private const val UNAUTHORIZED = 401
        private const val FORBIDDEN = 403
        private const val NOT_FOUND = 404
        private const val REQUEST_TIMEOUT = 408

        // 服务端错误
        private const val INTERNAL_SERVER_ERROR = 500
        private const val BAD_GATEWAY = 502
        private const val SERVICE_UNAVAILABLE = 503
        private const val GATEWAY_TIMEOUT = 504

        @JvmStatic
        fun handlerException(t: Throwable?): ResponseThrowable? {
            return when (t) {
                is HttpException -> {
                    val message = when (t.code()) {
                        UNAUTHORIZED, FORBIDDEN, NOT_FOUND, REQUEST_TIMEOUT, GATEWAY_TIMEOUT,
                        INTERNAL_SERVER_ERROR, BAD_GATEWAY, SERVICE_UNAVAILABLE -> {
                            ""
                        }
                        else -> {
                            "网络错误"
                        }
                    }
                    ResponseThrowable(t, t.code(), message)
                }
                is ServerException -> {
                    ResponseThrowable(t, t.code, t.message)
                }
                is JsonParseException, is JSONException, is ParseException -> {
                    // json解析错误
                    ResponseThrowable(t, ERROR.PARSE_ERROR, "解析错误")
                }
                is ConnectException -> {
                    ResponseThrowable(t, ERROR.TIMEOUT_ERROR, "连接失败")
                }
                is SSLHandshakeException -> {
                    ResponseThrowable(t, ERROR.SSL_ERROR, "证书验证失败")
                }
                is ConnectTimeoutException -> {
                    ResponseThrowable(t, ERROR.TIMEOUT_ERROR, "连接超时")
                }
                else -> {
                    t?.let { ResponseThrowable(it, ERROR.UNKNOWN, "未知错误") }
                }
            }
        }
    }
}

/**
 * 约定异常
 */
object ERROR {
    /**
     * 未知错误
     */
    const val UNKNOWN = 1000

    /**
     * 解析错误
     */
    const val PARSE_ERROR = 1001

    /**
     * 网络错误
     */
    const val NETWORK_ERROR = 1001

    /**
     * 协议错误
     */
    const val HTTP_ERROR = 1003

    /**
     * 证书错误
     */
    const val SSL_ERROR = 1005

    /**
     * 网络超时
     */
    const val TIMEOUT_ERROR = 1006
}