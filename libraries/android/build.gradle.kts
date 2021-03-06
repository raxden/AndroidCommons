import com.raxdenstudios.publishing.model.Coordinates

plugins {
  id("com.raxdenstudios.android-versioning")
  id("com.raxdenstudios.android-library")
  id("com.raxdenstudios.publish-library")
}

versioning {
  group = "com.raxdenstudios"
}

publishLibrary {
  name = "Android"
  description = "Android library"
  url = "https://github.com/raxden/android-commons"
  developerId = "raxden"
  developerName = "Ángel Gómez"
  developerEmail = "raxden.dev@gmail.com"
  coordinates = Coordinates.default.copy(artifactId = "commons-android")
}

android {
  buildTypes {
    getByName("debug") {
      isTestCoverageEnabled = true
    }
  }
  buildFeatures {
    viewBinding = true
  }
}

dependencies {
  api(project(Modules.libraryBase))

  api(AndroidLibraries.kotlinCore)
  api(AndroidLibraries.kotlinActivity)
  api(AndroidLibraries.kotlinFragment)
  api(AndroidLibraries.kotlinPreferences)
  api(AndroidLibraries.material)
  api(AndroidLibraries.playCore)
  api(AndroidLibraries.constraintLayout)
  api(AndroidLibraries.swipeRefreshLayout)
  api(AndroidLibraries.browser)
  api(AndroidLibraries.lifecycleExtensions)
  api(AndroidLibraries.lifecycleRuntime)
  api(AndroidLibraries.lifecycleCommon)
  api(AndroidLibraries.lifecycleViewModel)

  testImplementation(TestLibraries.atslJunit)
}
