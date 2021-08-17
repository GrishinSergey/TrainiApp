@file:Suppress("MemberVisibilityCanBePrivate", "SpellCheckingInspection")

object AndroidX {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompatVersion}"
    const val cardview = "androidx.cardview:cardview:${Versions.cardviewVersion}"
    const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerViewVersion}"
    const val legacy = "androidx.legacy:legacy-support-v4:${Versions.legacySupportV4Version}"
    const val gridlayout = "androidx.gridlayout:gridlayout:${Versions.gridLayoutVersion}"
    const val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime:${Versions.lifecycleVersion}"
    const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:2.2.0"
    const val lifecycleViewmodelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleVersion}"
    const val lifecycleProcess = "androidx.lifecycle:lifecycle-process:${Versions.lifecycleVersion}"
    const val lifecycleJava8Compiler = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycleVersion}"
    const val vectordrawable = "androidx.vectordrawable:vectordrawable:${Versions.vectorDrawableVersion}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtxVersion}"
    const val securityCrypto = "androidx.security:security-crypto:${Versions.securityVersion}"
    const val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigationVersion}"
    const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigationVersion}"
    const val roomRuntime = "androidx.room:room-runtime:${Versions.roomVersion}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.roomVersion}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.roomVersion}"
    const val activityKtx = "androidx.activity:activity-ktx:1.2.0-alpha05"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:1.3.0-alpha05"
    const val pagingRuntime = "androidx.paging:paging-runtime-ktx:3.0.0-alpha07"
    const val startupRuntime = "androidx.startup:startup-runtime:1.0.0"
}

object Third {
    const val javaxAnnotation = "javax.annotation:jsr250-api:1.0"
    const val javaxInject = "javax.inject:javax.inject:1"
    const val rxJava2 = "io.reactivex.rxjava2:rxjava:${Versions.rxJava2Version}"
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroidVersion}"
    const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlinVersion}"

    const val expandablelayout = "net.cachapa.expandablelayout:expandablelayout:2.9.2"
    const val recyclerviewAnimators = "jp.wasabeef:recyclerview-animators:2.2.7"
    const val shimmerlayout = "io.supercharge:shimmerlayout:2.1.0"
    const val fabSpeed = "io.github.yavski:fab-speed-dial:1.0.6"

    const val desugaring = "com.android.tools:desugar_jdk_libs:1.1.5"
    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinVersion}"

    const val runtimePermissions = "ru.superjob:kotlin-permissions:1.0.3"

    const val jsoup = "org.jsoup:jsoup:1.12.1"

    const val tedpermission = "gun0912.ted:tedpermission:1.0.3"

    const val flowlayoutmanager = "com.xiaofeng.android:flowlayoutmanager:1.2.3.2"

    const val viewBindingPropertyDelegate = "com.kirich1409.viewbindingpropertydelegate:vbpd-noreflection:1.4.1"

}

object Testing {
    const val robolectricShadowsMultidex = "org.robolectric:shadows-multidex:${Versions.robolectricShadowsMultidexVersion}"
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectricVersion}"
    const val archCore = "androidx.arch.core:core-testing:${Versions.archCoreVersions}"
    const val room = "androidx.room:room-testing:${Versions.roomVersion}"
    const val junit = "junit:junit:${Versions.junitVersion}"
    const val mockito = "org.mockito:mockito-core:${Versions.mockitoVersions}"
    const val mockk = "io.mockk:mockk:${Versions.mockkVersion}"
    const val androidxTestRunner = "androidx.test:runner:${Versions.androidxTestRunnerVersion}"
    const val androidxTestJunitExt = "androidx.test.ext:junit:${Versions.androidxTestJunitExtVersion}"
}

object Debug {
    const val slf4j = "org.slf4j:slf4j-jdk14:${Versions.slf4jVersion}"
}

object Google {
    const val firebaseMessaging = "com.google.firebase:firebase-messaging:${Versions.firebaseMessagingVersion}"
    const val firebaseCrashlytics = "com.google.firebase:firebase-crashlytics:${Versions.firebaseCrashlyticsLibraryVersion}"
    const val material = "com.google.android.material:material:${Versions.materialVersion}"
    const val dagger = "com.google.dagger:dagger:${Versions.dagger2Version}"
    const val daggerAndroid = "com.google.dagger:dagger-android:${Versions.dagger2Version}"
    const val daggerAndroidSupport = "com.google.dagger:dagger-android-support:${Versions.dagger2Version}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger2Version}"
    const val daggerAndroidProcessor = "com.google.dagger:dagger-android-processor:${Versions.dagger2Version}"
}
