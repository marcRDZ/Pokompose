plugins {
    id(BuildPlugins.javaLibrary)
    kotlin(BuildPlugins.jvm)
    id(BuildPlugins.kapt)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    api(project(":core:domain"))

    implementation(Libraries.hilt)
    kapt(Libraries.hiltCompiler)

    implementation(Libraries.arrowCore)
    implementation(Libraries.kotlinCoroutinesCore)

    testImplementation(TestLibraries.junit4)
    testImplementation(TestLibraries.mockk)
    testImplementation(TestLibraries.mockkCommon)
    testImplementation(TestLibraries.coroutineTest)

}