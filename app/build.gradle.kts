plugins {
    kotlin("kapt")
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
}

android {
    namespace = AndroidConfig.namespace
    compileSdk = AndroidConfig.Sdk.compile
    

    defaultConfig {
        applicationId = AndroidConfig.namespace
        minSdk = AndroidConfig.Sdk.min
        targetSdk = AndroidConfig.Sdk.target
        versionCode = AndroidConfig.Version.code
        versionName = AndroidConfig.Version.name

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {

        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures{
        compose = true
    }

    composeOptions{
        kotlinCompilerExtensionVersion = "1.4.4"
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
    implementation(project(mapOf("path" to ":data")))
    implementation(project(mapOf("path" to ":domain")))

    implementation(libs.androidCore.ktx)
    implementation(libs.androidStartUp.runtime)
    implementation(libs.kotlinx.coroutines.android)


    implementation(libs.androidLifecycle.runtimeKtx)
    implementation(libs.androidLifecycle.viewModelCompose)


    //Compose x Coil
    implementation(libs.bundles.composeBundle)
    debugImplementation(libs.bundles.composeDebugTestBundle)

    //Hilt
    kapt(libs.dagger.hiltAndroidCompiler)
    implementation(libs.dagger.hiltAndroid)
    implementation(libs.androidHilt.navigationCompose)

    implementation(libs.androidRoom.runtime)
    implementation(libs.androidRoom.ktx)

    testImplementation(libs.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.androidx.test.ext.junit)
}