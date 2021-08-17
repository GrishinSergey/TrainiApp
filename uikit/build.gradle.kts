import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
    id("org.jetbrains.dokka")
}

android {
    compileSdkVersion(ProjectCompileConfig.compileSdkVersion)
    buildToolsVersion(ProjectCompileConfig.buildToolsVersion)

    defaultConfig {
        minSdkVersion(ProjectCompileConfig.minSdkVersion)
        targetSdkVersion(ProjectCompileConfig.targetSdkVersion)

        versionCode = ProjectCompileConfig.versionCode

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
        coreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
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