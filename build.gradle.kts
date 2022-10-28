buildscript {
    dependencies {
        classpath(BuildPlugins.hiltGradlePlugin)
        classpath("com.android.tools.build:gradle:7.3.1")
    }
}

plugins {
    id(BuildPlugins.androidApplication) version BuildPlugins.Versions.gradle apply false
    id(BuildPlugins.androidLibrary) version BuildPlugins.Versions.gradle apply false
    id(BuildPlugins.kotlinAndroid) version kotlinVersion apply false
    kotlin(BuildPlugins.jvm) version kotlinVersion apply false
}

tasks.register("clean").configure {
    delete("build")
}
