@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    kotlin("kapt")
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
}

android {
    namespace = AndroidConfig.namespace+".note.presentation"
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
    implementation(Deps.Android.coreKtx)
    implementation(Deps.Android.appcompat)
    implementation(Deps.Coroutines.android)

    //Hilt
    kapt(Deps.Hilt.compiler)
    implementation(Deps.Hilt.android)
    implementation(Deps.Hilt.navigation)

    //Compose x Coil
    implementation(Deps.Compose.ui)
    implementation(Deps.Compose.tooling)
    implementation(Deps.Compose.material3)
    implementation(Deps.Coil.compose)

    implementation(project(":features:note:domain"))
    implementation(project(":core:navigation"))
    implementation(project(":core:ui"))
}