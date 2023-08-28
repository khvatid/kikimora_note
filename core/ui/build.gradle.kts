@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    kotlin("kapt")
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
}

android {
    namespace = "khvatid.core.ui"
    compileSdk = 33
    defaultConfig{
        minSdk = 27
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures{
        compose = true
    }

    composeOptions{
        kotlinCompilerExtensionVersion = "1.4.4"
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(libs.androidCore.ktx)
    implementation(libs.kotlinx.coroutines.android)

    //Hilt
    kapt(libs.dagger.hiltAndroidCompiler)
    implementation(libs.dagger.hiltAndroid)

    //Compose
    implementation(libs.androidCompose.ui)
    implementation(libs.androidCompose.material3)
    implementation(libs.androidCompose.tooling)
    implementation(libs.androidNavigation.compose)

    implementation(libs.androidLifecycle.runtimeKtx)
    implementation(libs.androidLifecycle.viewModelCompose)

    implementation(project(":core:navigation"))
}