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
    implementation(Deps.Android.coreKtx)
    implementation(Deps.Coroutines.android)

    //Hilt
    kapt(Deps.Hilt.compiler)
    implementation(Deps.Hilt.android)

    //Compose
    implementation(Deps.Compose.ui)
    implementation(Deps.Compose.material3)
    implementation(Deps.Compose.tooling)
    implementation(Deps.Android.navigationCompose)

    implementation(Deps.Lifecycle.runtimeKtx)
    implementation(Deps.Lifecycle.viewModelCompose)

    implementation(project(":core:navigation"))
}