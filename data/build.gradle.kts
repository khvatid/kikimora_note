@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    kotlin("kapt")
}

android {
    namespace = AndroidConfig.namespace+".data"
    compileSdk = AndroidConfig.Sdk.compile
    compileOptions {
        sourceCompatibility = AndroidConfig.javaVersion
        targetCompatibility = AndroidConfig.javaVersion
    }
    kotlinOptions {
        jvmTarget = AndroidConfig.jvmTarget
    }
}

dependencies {

    implementation(libs.androidRoom.ktx)
    kapt(libs.androidRoom.compiler)
    implementation(libs.androidRoom.paging)
    implementation(libs.androidRoom.runtime)

    implementation(libs.androidCore.ktx)
    implementation(libs.kotlinx.coroutines.android)


}