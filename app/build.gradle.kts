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
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":core:ui"))
    implementation(project(":core:navigation"))
    implementation(project(":features:listNotes:domain"))
    implementation(project(":features:listNotes:presentation"))
    implementation(project(":features:note:domain"))
    implementation(project(":features:note:presentation"))


    implementation(Deps.Android.coreKtx)
    implementation(Deps.Android.startUpRuntime)
    implementation(Deps.Android.navigationCompose)
    implementation(Deps.Android.activityCompose)


    implementation(Deps.Coroutines.android)

    implementation(Deps.Lifecycle.runtimeKtx)
    implementation(Deps.Lifecycle.viewModelCompose)

    //Compose
    implementation(Deps.Compose.ui)
    implementation(Deps.Compose.tooling)
    implementation(Deps.Compose.toolingPreview)
    implementation(Deps.Compose.material3)

    //Hilt
    kapt(Deps.Hilt.compiler)
    implementation(Deps.Hilt.android)
    implementation(Deps.Hilt.navigation)
}