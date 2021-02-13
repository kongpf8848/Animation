import org.gradle.api.JavaVersion

object Config{

    const val compileSdkVersion = 30
    const val buildToolsVersion = "30.0.2"
    const val applicationId="com.github.kongpf8848.animation"
    const val minSdkVersion = 21
    const val targetSdkVersion = 30
    val sourceCompatibilityVersion = JavaVersion.VERSION_1_8
    val targetCompatibilityVersion = JavaVersion.VERSION_1_8
    const val versionCode = 5
    const val versionName = "1.1.3"

}

object Versions {

    const val PLUGIN_GRADLE_VERSION="3.6.3"
    const val KOTLIN_VERSION = "1.3.72"


    const val ANDROIDX_APP_COMPAT_VERSION = "1.2.0"
    const val ANDROIDX_LEGACY_V4_VERSION = "1.0.0"
    const val ANDROIDX_LEGACY_V13_VERSION = "1.0.0"
    const val ANDROIDX_MULTIDEX_VERSION = "2.0.1"
    const val ANDROIDX_MATERIAL_VERSION = "1.3.0"
    const val ANDROIDX_RECYCLERVIEW_VERSION = "1.1.0"
    const val ANDROIDX_PREFERENCE_VERSION = "1.1.1"
    const val ANDROIDX_CARDVIEW_VERSION = "1.0.0"
    const val ANDROIDX_GRIDLAYOUT_VERSION = "1.0.0"
    const val ANDROIDX_ANNOTATION_VERSION = "1.1.0"
    const val ANDROIDX_CONSTRAINTLAYOUT_VERSION = "2.0.4"
    const val ANDROIDX_PAGING_VERSION = "2.1.2"
    const val ANDROIDX_WORK_RUNTIME_VERSION = "2.4.0"
    const val ANDROIDX_CORE_VERSION="1.3.2"
    const val ANDROIDX_CORE_KTX_VERSION="1.3.2"
    const val ANDROIDX_ROOM_VERSION="2.2.5"
    const val ANDROIDX_COORDINATORLAYOUT_VERSION="1.1.0"
    const val ANDROIDX_DATABINDING_VERSION="4.1.0"
    const val ANDROIDX_ACTIVITY_VERSION="1.2.0-alpha06"
    const val ANDROIDX_FRAGMENT_VERSION="1.3.0-alpha06"
    const val ANDROIDX_LIFECYCLE_VERSION="2.2.0"
    const val ANDROIDX_LIFECYCLE_ARCH_VERSION="2.1.0"
    const val ANDROIDX_VIEWPAGER_VERSION="1.1.0"


    const val GSON_VERSION = "2.8.2"
    const val JUNIT_VERSION = "4.12"
    const val IMMERSIONBAR_VERSION = "3.0.0"
    const val GLIDE_VERSION = "4.9.0"
    const val SWIPEDELMENULAYOUT_VERSION = "V1.3.0"
    const val PHOTOVIEW_VERSION = "2.1.3"
    const val KOTLINX_COROUTINES_ANDROID_VERSION = "1.3.9"
    const val BUTTERKNIFE_VERSION="10.2.1"
    const val MAGIC_INDICATOR_VERSION="1.5.0"
    const val LOTTIE_VERSION="3.4.0"
    const val COMMON_HELPER_VERSION="1.0.21"
    const val CIRCLE_IMAGEVIEW_VERSION="2.1.0"

}

object BuildDependencies {

    const val junit = "junit:junit:${Versions.JUNIT_VERSION}"
    const val immersionbar = "com.gyf.immersionbar:immersionbar:${Versions.IMMERSIONBAR_VERSION}"
    const val immersionbarComponents = "com.gyf.immersionbar:immersionbar-components:${Versions.IMMERSIONBAR_VERSION}"
    const val immersionbarKtx = "com.gyf.immersionbar:immersionbar-ktx:${Versions.IMMERSIONBAR_VERSION}"
    const val kotlinStdlibJdk7 = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.KOTLIN_VERSION}"
    const val kotlinxCoroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.KOTLINX_COROUTINES_ANDROID_VERSION}"

    const val butterknife="com.jakewharton:butterknife:${Versions.BUTTERKNIFE_VERSION}"
    const val butterknifeCompiler="com.jakewharton:butterknife-compiler:${Versions.BUTTERKNIFE_VERSION}"
    const val glide = "com.github.bumptech.glide:glide:${Versions.GLIDE_VERSION}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.GLIDE_VERSION}"
    const val glideIntegration="com.github.bumptech.glide:okhttp3-integration:${Versions.GLIDE_VERSION}"
    const val photoView = "com.github.chrisbanes:PhotoView:${Versions.PHOTOVIEW_VERSION}"
    const val magicIndicator ="com.github.hackware1993:MagicIndicator:${Versions.MAGIC_INDICATOR_VERSION}"
    const val lottie="com.airbnb.android:lottie:${Versions.LOTTIE_VERSION}"
    const val commonHelper="com.kongpf.commonhelper:common-helper:${Versions.COMMON_HELPER_VERSION}"
    const val circleImageView="de.hdodenhof:circleimageview:${Versions.CIRCLE_IMAGEVIEW_VERSION}"

}

object AndroidX {
    const val appCompat = "androidx.appcompat:appcompat:${Versions.ANDROIDX_APP_COMPAT_VERSION}"
    const val legacyV4 = "androidx.legacy:legacy-support-v4:${Versions.ANDROIDX_LEGACY_V4_VERSION}"
    const val legacyV13 = "androidx.legacy:legacy-support-v13:${Versions.ANDROIDX_LEGACY_V13_VERSION}"
    const val multidex = "androidx.multidex:multidex:${Versions.ANDROIDX_MULTIDEX_VERSION}"
    const val material = "com.google.android.material:material:${Versions.ANDROIDX_MATERIAL_VERSION}"
    const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.ANDROIDX_RECYCLERVIEW_VERSION}"
    const val preference = "androidx.preference:preference:${Versions.ANDROIDX_PREFERENCE_VERSION}"
    const val cardview = "androidx.cardview:cardview:${Versions.ANDROIDX_CARDVIEW_VERSION}"
    const val gridlayout = "androidx.gridlayout:gridlayout:${Versions.ANDROIDX_GRIDLAYOUT_VERSION}"
    const val annotationX = "androidx.annotation:annotation:${Versions.ANDROIDX_ANNOTATION_VERSION}"
    const val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.ANDROIDX_CONSTRAINTLAYOUT_VERSION}"
    const val paging = "androidx.paging:paging-runtime:${Versions.ANDROIDX_PAGING_VERSION}"
    const val work = "androidx.work:work-runtime:${Versions.ANDROIDX_WORK_RUNTIME_VERSION}"
    const val core="androidx.core:core:${Versions.ANDROIDX_CORE_VERSION}"
    const val coreKtx="androidx.core:core-ktx:${Versions.ANDROIDX_CORE_KTX_VERSION}"
    const val room="androidx.room:room-runtime:${Versions.ANDROIDX_ROOM_VERSION}"
    const val roomKtx="androidx.room:room-ktx:${Versions.ANDROIDX_ROOM_VERSION}"
    const val roomTestHelper="androidx.room:room-testing:${Versions.ANDROIDX_ROOM_VERSION}"
    const val roomCompiler="androidx.room:room-compiler:${Versions.ANDROIDX_ROOM_VERSION}"
    const val roomRxJava2= "androidx.room:room-rxjava2:${Versions.ANDROIDX_ROOM_VERSION}"
    const val coordinatorlayout="androidx.coordinatorlayout:coordinatorlayout:${Versions.ANDROIDX_COORDINATORLAYOUT_VERSION}"
    const val databindingCommon="androidx.databinding:databinding-common:${Versions.ANDROIDX_DATABINDING_VERSION}"
    const val databindingAdapters="androidx.databinding:databinding-adapters:${Versions.ANDROIDX_DATABINDING_VERSION}"
    const val databindingRuntime="androidx.databinding:databinding-runtime:${Versions.ANDROIDX_DATABINDING_VERSION}"
    const val activity="androidx.activity:activity:${Versions.ANDROIDX_ACTIVITY_VERSION}"
    const val activityKtx="androidx.activity:activity-ktx:${Versions.ANDROIDX_ACTIVITY_VERSION}"
    const val fragment="androidx.fragment:fragment:${Versions.ANDROIDX_FRAGMENT_VERSION}"
    const val fragmentKtx="androidx.fragment:fragment-ktx:${Versions.ANDROIDX_FRAGMENT_VERSION}"
    const val lifecycleViewmodelKtx="androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.ANDROIDX_LIFECYCLE_VERSION}"
    const val lifecycleLivedataKtx="androidx.lifecycle:lifecycle-livedata-ktx:${Versions.ANDROIDX_LIFECYCLE_VERSION}"
    const val lifecycleRuntimeKtx="androidx.lifecycle:lifecycle-runtime-ktx:${Versions.ANDROIDX_LIFECYCLE_VERSION}"
    const val lifecycleViewmodelSavedstate="androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.ANDROIDX_LIFECYCLE_VERSION}"
    const val lifecycleCompiler="androidx.lifecycle:lifecycle-compiler:${Versions.ANDROIDX_LIFECYCLE_VERSION}"
    const val lifecycleCommonJava8 = "androidx.lifecycle:lifecycle-common-java8:${Versions.ANDROIDX_LIFECYCLE_VERSION}"
    const val lifecycleService = "androidx.lifecycle:lifecycle-service:${Versions.ANDROIDX_LIFECYCLE_VERSION}"
    const val lifecycleProcess = "androidx.lifecycle:lifecycle-process:${Versions.ANDROIDX_LIFECYCLE_VERSION}"
    const val lifecycleReactivestreamsKtx= "androidx.lifecycle:lifecycle-reactivestreams-ktx:${Versions.ANDROIDX_LIFECYCLE_VERSION}"
    const val arcCoreTesting="androidx.arch.core:core-testing:${Versions.ANDROIDX_LIFECYCLE_ARCH_VERSION}"
}



