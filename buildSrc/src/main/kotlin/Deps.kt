object Deps {

    object Android {
        private const val coreVersion = "1.9.0"
        private const val splashscreenVersion = "1.1.0-alpha01"
        private const val activityVersion = "1.7.0"
        private const val startUpVersion = "1.1.1"
        private const val appcompatVersion = "1.6.1"
        private const val navigationVersion = "2.5.3"
        private const val materialVersion = "1.8.0"

        const val coreKtx: String = "androidx.core:core-ktx:$coreVersion"
        const val splashScreen = "androidx.core:core-splashscreen:$splashscreenVersion"
        const val activityCompose = "androidx.activity:activity-compose:$activityVersion"
        const val navigationCompose = "androidx.navigation:navigation-compose:$navigationVersion"

        const val material = "com.google.android.material:material:$materialVersion"

        const val startUpRuntime = "androidx.startup:startup-runtime:$startUpVersion"
        const val appcompat = "androidx.appcompat:appcompat:$appcompatVersion"

    }

    object Lifecycle{
        private const val version = "2.6.1"

        const val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:$version"
        const val runtimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
    }

    object Room {
        private const val version = "2.5.1"
        const val ktx = "androidx.room:room-ktx:$version"
        const val paging = "androidx.room:room-paging:$version"
        const val runtime = "androidx.room:room-runtime:$version"
        const val compiler = "androidx.room:room-compiler:$version"
        const val testing = "androidx.room:room-testing:$version"

    }

    object Compose {
        private const val version = "1.4.0"
        private const val material3Version = "1.0.1"

        const val ui = "androidx.compose.ui:ui:$version"
        const val toolingPreview = "androidx.compose.ui:ui-tooling-preview:$version"
        const val tooling = "androidx.compose.ui:ui-tooling:$version"
        const val uiTestManifest = "androidx.compose.ui:ui-test-manifest:$version"
        const val material3 = "androidx.compose.material3:material3:$material3Version"
    }

    object Datastore{
        private const val version = "1.0.0"
        const val preferences = "androidx.datastore:datastore-preferences:$version"
    }

    object Coil{
        private const val version = "2.3.0"
        const val compose = "io.coil-kt:coil-compose:$version"
    }

    object Coroutines{
        private const val version = "1.7.1"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
    }

    object SquareUp{
        object Retrofit{
            private const val version = "2.9.0"
            const val retrofit2 = "com.squareup.retrofit2:retrofit:$version"
            const val converterGson = "com.squareup.retrofit2:converter-gson:$version"
        }

        object OkHttp {
            private const val version = "4.10.0"
            const val okhttp3 = "com.squareup.okhttp3:okhttp:$version"
            const val interceptor = "com.squareup.okhttp3:logging-interceptor:$version"
        }
    }

    object Hilt{
        private const val version = "2.44"
        private const val navigationVersion = "1.0.0"
        const val android = "com.google.dagger:hilt-android:$version"
        const val compiler = "com.google.dagger:hilt-android-compiler:$version"
        const val navigation = "androidx.hilt:hilt-navigation-compose:$navigationVersion"
    }


}
