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

tasks.withType<Test>{
    useJUnitPlatform()
}


dependencies {

    implementation(Deps.Room.ktx)
    implementation(Deps.Room.paging)
    implementation(Deps.Room.runtime)
    kapt(Deps.Room.compiler)
    testImplementation(Deps.Room.testing)

    implementation(Deps.Android.coreKtx)
    implementation(Deps.Coroutines.android)


}