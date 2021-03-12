package com.raxdenstudios.commons.retrofit.interceptor

import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber

// TODO -> CUSTOM LOGGER
class HttpLoggingTimberInterceptorLogger: HttpLoggingInterceptor.Logger {

  override fun log(message: String) {
    Timber.tag("OkHttp").d(message)
  }
}