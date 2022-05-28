const val kotlinVersion = "1.5.21"

object BuildPlugins {

    object Versions {
        const val gradle = "7.1.2"
        const val hilt = "2.41"
    }

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.gradle}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val hiltGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
    const val hilt = "dagger.hilt.android.plugin"
    const val kotlinAndroid = "org.jetbrains.kotlin.android"
    const val androidApplication = "com.android.application"
    const val androidLibrary = "com.android.library"
    const val javaLibrary = "java-library"
    const val kapt = "kotlin-kapt"
    const val jvm = "jvm"
}

object AndroidBuildConfig {

    const val minSdk = 21
    const val compileSdk = 31
    const val targetSdk = compileSdk

    const val composeVersion = "1.0.2"

    const val testRunner = "androidx.test.runner.AndroidJUnitRunner"
}

object Libraries {

    private object Versions {
        const val ktx = "1.7.0"
        const val ktxLifecycle = "2.3.1"
        const val appCompat = "1.3.1"
        const val material = "1.4.0"
        const val composeActivity = "1.3.1"
        const val coroutines = "1.3.9"
        const val okHttp = "4.9.3"
        const val retrofit = "2.9.0"
        const val arrow = "0.11.0"
    }

    const val ktxCore = "androidx.core:core-ktx:${Versions.ktx}"
    const val ktxLifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.ktxLifecycle}"
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"
    const val kotlinCoroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val kotlinCoroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitGsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"

    const val arrowCore = "io.arrow-kt:arrow-core:${Versions.arrow}"
    const val arrowSyntax = "io.arrow-kt:arrow-syntax:${Versions.arrow}"
    const val arrowMeta = "io.arrow-kt:arrow-meta:${Versions.arrow}"

    const val hiltAndroid = "com.google.dagger:hilt-android:${BuildPlugins.Versions.hilt}"
    const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${BuildPlugins.Versions.hilt}"
    const val hilt = "com.google.dagger:hilt-core:${BuildPlugins.Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:${BuildPlugins.Versions.hilt}"

    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val composeUI = "androidx.compose.ui:ui:${AndroidBuildConfig.composeVersion}"
    const val composeMaterial = "androidx.compose.material:material:${AndroidBuildConfig.composeVersion}"
    const val composeUIToolingPreview = "androidx.compose.ui:ui-tooling-preview:${AndroidBuildConfig.composeVersion}"
    const val composeActivity = "androidx.activity:activity-compose:${Versions.composeActivity}"
    const val composeUITooling = "androidx.compose.ui:ui-tooling:${AndroidBuildConfig.composeVersion}"
}

object TestLibraries {

    private object Versions {
        const val junit4 = "4.13.2"
        const val androidJunit = "1.1.3"
        const val espresso = "3.4.0"
    }

    const val junit4 = "junit:junit:${Versions.junit4}"
    const val androidJunit = "androidx.test.ext:junit:${Versions.androidJunit}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val composeUIJunit4 = "androidx.compose.ui:ui-test-junit4:${AndroidBuildConfig.composeVersion}"
    const val hiltAndroid = "com.google.dagger:hilt-android-testing:${BuildPlugins.Versions.hilt}"

}