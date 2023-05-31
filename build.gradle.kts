ext {
    extra["kotlin_version"] = "1.8.10"
    extra["hilt_version"] = "2.46.1"
    extra["compose_compiler_version"] = "1.4.7"
    extra["compose_material3_version"] = "1.2.0-alpha02"
    extra["compose_ui_version"] = "1.5.0-beta01"
}

buildscript {
    val agpVersion by extra("8.2.0-alpha05")
    dependencies {
        classpath("com.android.tools.build:gradle:$agpVersion")
        classpath("com.google.android.gms:oss-licenses-plugin:0.10.6")
        classpath("com.google.gms:google-services:4.3.15")
        classpath("com.google.firebase:firebase-crashlytics-gradle:2.9.5")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.21")
        classpath("com.google.protobuf:protobuf-gradle-plugin:0.9.3")
    }
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

plugins {
    id("com.android.application") version "8.0.2" apply false
    id("com.android.library") version "8.0.2" apply false
    id("org.jetbrains.kotlin.android") version "1.8.21" apply false
    id("com.google.dagger.hilt.android") version "2.46.1" apply false
    id("com.google.protobuf") version "0.9.3" apply false
}