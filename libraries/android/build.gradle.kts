import com.raxdenstudios.publishing.model.Coordinates

plugins {
  id("com.raxdenstudios.android-versioning")
  id("com.raxdenstudios.android-library")
  id("com.raxdenstudios.publish-library")
  id("com.raxdenstudios.android-jacoco")
}

versioning {
  versionFilePath = "./config/version.properties"
  group = "com.raxdenstudios"
}

publishLibrary {
  name = "Android Commons"
  description = "Android commons is a library with a set of useful classes to help to developer to work with android."
  url = "https://github.com/raxden/android-commons"
  developerId = "raxden"
  developerName = "Ángel Gómez"
  developerEmail = "raxden.dev@gmail.com"
  coordinates = Coordinates.default.copy(artifactId = "commons-android")
}

android {
  buildFeatures {
    viewBinding = true
  }
}

dependencies {
  implementation(project(Modules.libraryKotlin))

  implementation(AndroidLibraries.kotlinCore)
  implementation(AndroidLibraries.kotlinActivity)
  implementation(AndroidLibraries.kotlinFragment)
  implementation(AndroidLibraries.kotlinPreferences)
  implementation(AndroidLibraries.material)
  implementation(AndroidLibraries.playCore)
  implementation(AndroidLibraries.constraintLayout)
  implementation(AndroidLibraries.swipeRefreshLayout)
  implementation(AndroidLibraries.browser)
  implementation(AndroidLibraries.lifecycleExtensions)
  implementation(AndroidLibraries.lifecycleRuntime)
  implementation(AndroidLibraries.lifecycleCommon)
  implementation(AndroidLibraries.lifecycleViewModel)
}
