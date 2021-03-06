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

        testInstrumentationRunner = ProjectCompileConfig.testInstrumentationRunner
    }

    buildTypes {
        getByName("debug") {
        }

        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
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

    api(Third.stdlib)
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.1")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.1")

    api(Google.material)
    api(Google.dagger)
    api(Google.daggerAndroid)
    api(Google.daggerAndroidSupport)

    api(AndroidX.coreKtx)
    api(AndroidX.securityCrypto)
    api(AndroidX.lifecycleRuntime)
    api(AndroidX.lifecycleProcess)
    api(AndroidX.lifecycleExtensions)
    api(AndroidX.lifecycleViewmodelKtx)
    api(AndroidX.constraintlayout)
    api(AndroidX.appcompat)
    api(AndroidX.pagingRuntime)
    api(AndroidX.legacy)
    api(AndroidX.recyclerview)
    api(AndroidX.cardview)
    api(AndroidX.navigationFragmentKtx)
    api(AndroidX.navigationUiKtx)
    api(AndroidX.activityKtx)
    api(AndroidX.fragmentKtx)
    api(AndroidX.startupRuntime)

    api(Third.rxJava2)
    api(Third.rxAndroid)
    api(Third.rxKotlin)

    api(Third.expandablelayout)
    api(Third.recyclerviewAnimators)
    api(Third.shimmerlayout)
    api(Third.fabSpeed)
    api(Third.runtimePermissions)

    api(Third.viewBindingPropertyDelegate)

    annotationProcessor(Google.daggerCompiler)
    annotationProcessor(Google.daggerAndroidProcessor)
}

repositories {
    mavenCentral()
}