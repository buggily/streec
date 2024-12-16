pluginManagement {
    includeBuild("builder")

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

rootProject.name = "streec"
include(":app")

include(":core:ui")
include(":core:domain")
include(":core:local")

include(":domain:streec")
include(":data:streec")
include(":local:streec")

include(":feature:streecs")
include(":core:data")
