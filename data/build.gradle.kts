@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    kotlin("kapt")
}

android {
    namespace = AndroidConfig.namespace+".data"
    compileSdk = AndroidConfig.Sdk.compile
    defaultConfig {
        minSdk = AndroidConfig.Sdk.min
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

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

    api(project(":features:listNotes:domain"))
    api(project(":features:note:domain"))

    implementation(Deps.Room.ktx)
    implementation(Deps.Room.paging)
    implementation(Deps.Room.runtime)
    kapt(Deps.Room.compiler)
    testImplementation(Deps.Room.testing)

    implementation(Deps.Android.coreKtx)
    implementation(Deps.Coroutines.android)
    testImplementation(Deps.Coroutines.test)

    testImplementation("androidx.test:core:1.5.0")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")


    androidTestImplementation ("androidx.test.ext:junit-ktx:1.1.5")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation ("com.google.truth:truth:1.1.2")
    androidTestImplementation ("androidx.arch.core:core-testing:2.2.0")
    androidTestImplementation ("androidx.test:rules:1.5.0")
    androidTestImplementation ("androidx.test:runner:1.5.2")
    androidTestImplementation ("androidx.test:core:1.5.0")

}