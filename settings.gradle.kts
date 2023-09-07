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
include(":features:listNotes:domain")
include(":features:listNotes:presentation")
include(":features:note:domain")
include(":features:note:presentation")
include(":features:settings:domain")
