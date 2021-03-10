plugins {
  id("com.raxdenstudios.android-library")
}

dependencies {
  api(platform(Libraries.okHttpBom))
  api(Libraries.okHttp)
  api(Libraries.okHttpLoggingInterceptor)
  
  implementation(Libraries.timber)
}
