pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Kikimora"
include(":app")
include(":data")
include(":domain")
include(":core:ui")
include(":core:navigation")
include(":features:notes:domain")
include(":features:notes:presentation")
include(":features:settings:domain")
