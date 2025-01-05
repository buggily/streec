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
include(":core:data")
include(":core:domain")
include(":core:local")
include(":core:test")

include(":domain:streec")
include(":data:streec")
include(":local:streec")

include(":feature:streecs")
