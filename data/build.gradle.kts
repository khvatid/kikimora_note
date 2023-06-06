@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    kotlin("kapt")
}

android {
    namespace = "${AndroidConfig.namespace}.data"
    compileSdk = AndroidConfig.Sdk.compile

    defaultConfig {
        minSdk = AndroidConfig.Sdk.min

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = AndroidConfig.javaVersion
        targetCompatibility = AndroidConfig.javaVersion
    }
    kotlinOptions {
        jvmTarget = AndroidConfig.jvmTarget
    }
}

dependencies {

    implementation(project(mapOf("path" to ":domain")))
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidCore.ktx)
    implementation(libs.squareUp.okhttp3)
    implementation(libs.squareUp.okhttp3Interceptor)
    implementation(libs.squareUp.retrofit2)
    implementation(libs.squareUp.retrofit2ConverterGson)

    implementation (libs.androidx.datastore.preferences)


    implementation(libs.androidRoom.ktx)
    kapt(libs.androidRoom.compiler)
    implementation(libs.androidRoom.paging)
    implementation(libs.androidRoom.runtime)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
}