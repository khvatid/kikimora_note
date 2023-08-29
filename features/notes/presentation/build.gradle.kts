@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    kotlin("kapt")
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
}

android {
    namespace = AndroidConfig.namespace+".notes.presentation"
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
    implementation(libs.androidCore.ktx)
    implementation(libs.androidAppcompat)

    kapt(libs.dagger.hiltAndroidCompiler)
    implementation(libs.dagger.hiltAndroid)
    implementation(libs.androidHilt.navigationCompose)

    //Compose x Coil
    implementation(libs.androidCompose.ui)
    implementation(libs.androidCompose.material3)
    implementation(libs.androidCompose.tooling)

    implementation(project(":core:navigation"))
    implementation(project(":core:ui"))
}