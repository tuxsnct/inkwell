ext {
    extra["kotlin_version"] = "1.8.10"
    extra["hilt_version"] = "2.45"
    extra["compose_compiler_version"] = "1.4.3"
    extra["compose_material3_version"] = "1.2.0-alpha01"
    extra["compose_ui_version"] = "1.5.0-alpha04"
}

buildscript {
    dependencies {
        classpath("com.google.android.gms:oss-licenses-plugin:0.10.6")
        classpath("com.google.gms:google-services:4.3.15")
        classpath("com.google.firebase:firebase-crashlytics-gradle:2.9.5")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.10")
    }
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

plugins {
    id("com.android.application") version "8.0.1" apply false
    id("com.android.library") version "8.0.1" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("com.google.dagger.hilt.android") version "2.45" apply false
}