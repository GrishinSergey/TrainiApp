import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("org.jetbrains.dokka")
}

android {
    compileSdk = ProjectCompileConfig.compileSdkVersion
    buildToolsVersion = ProjectCompileConfig.buildToolsVersion

    defaultConfig {
        minSdk = ProjectCompileConfig.minSdkVersion
        targetSdk = ProjectCompileConfig.targetSdkVersion

        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        getByName("debug") {
        }

        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}

tasks.register("dokka", DokkaTask::class.java) {
    dokkaSourceSets {
        named("main") {
            noAndroidSdkLink.set(false)
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    coreLibraryDesugaring(Third.desugaring)

    implementation(project(":common"))
}