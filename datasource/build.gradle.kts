plugins {
    id(BuildPlugins.androidLibrary)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.hilt)
    id(BuildPlugins.kapt)
}

android {
    compileSdk = AndroidBuildConfig.compileSdk

    defaultConfig {
        minSdk = AndroidBuildConfig.minSdk
        targetSdk = AndroidBuildConfig.targetSdk

        testInstrumentationRunner = AndroidBuildConfig.testRunner
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        named("release").configure {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

}

dependencies {
    implementation(project(":core:data"))
    implementation(fileTree("libs") { include(listOf("*.jar", "*.aar")) })

    implementation(Libraries.hiltAndroid)
    kapt(Libraries.hiltAndroidCompiler)

    implementation(Libraries.ktxCore)
    implementation(Libraries.kotlinCoroutinesCore)

    implementation(Libraries.okHttp)
    implementation(Libraries.retrofit)
    implementation(Libraries.retrofit)

    implementation(Libraries.arrowCore)

    // For local unit tests
    testImplementation(TestLibraries.junit4)
    testImplementation(TestLibraries.hiltAndroid)
    kaptTest(Libraries.hiltCompiler)

    // For instrumentation tests
    androidTestImplementation(TestLibraries.androidJunit)
    androidTestImplementation(TestLibraries.espresso)
    androidTestImplementation(TestLibraries.hiltAndroid)
    kaptTest(Libraries.hiltCompiler)

}
