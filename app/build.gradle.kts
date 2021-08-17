import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("org.jetbrains.dokka")
//    id("com.google.gms.google-services")
//    id("com.google.firebase.crashlytics")
}

android {
    compileSdkVersion(ProjectCompileConfig.compileSdkVersion)
    buildToolsVersion(ProjectCompileConfig.buildToolsVersion)

    defaultConfig {
        applicationId = ProjectCompileConfig.applicationId

        minSdkVersion(ProjectCompileConfig.minSdkVersion)
        targetSdkVersion(ProjectCompileConfig.targetSdkVersion)

        versionCode = ProjectCompileConfig.versionCode

        vectorDrawables.useSupportLibrary = true

        testInstrumentationRunner = ProjectCompileConfig.testInstrumentationRunner

        buildConfigField("String", "APP_DATABASE_NAME", AppConstants.dataBaseName)
        buildConfigField("int", "APP_DATABASE_VERSION", AppConstants.dataBaseVersion)

        javaCompileOptions {
            annotationProcessorOptions {
                arguments = mapOf(
                    "room.schemaLocation" to "$projectDir/schemas",
                    "room.incremental" to "true"
                )
            }
        }

    }

    signingConfigs {
        create("release") {
//            storeFile = file(SignConfig.signingFilePath)
//            storePassword = SignConfig.signingStorePassword
//            keyAlias = SignConfig.signingKeyAlias
//            keyPassword = SignConfig.signingKeyPassword
        }
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
    implementation(project(":uikit"))

    implementation(Third.javaxAnnotation)
    implementation(Third.javaxInject)

    implementation(Google.firebaseMessaging)
    implementation(Google.firebaseCrashlytics)

    implementation(AndroidX.roomKtx)
    implementation(AndroidX.roomRuntime)

    kapt(Google.daggerCompiler)
    kapt(Google.daggerAndroidProcessor)
    kapt(AndroidX.lifecycleJava8Compiler)
    kapt(AndroidX.roomCompiler)
}

repositories {
    mavenCentral()
}