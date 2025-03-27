pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "Lucca"
include(":app")
include(":core")
include(":feature")
include(":core:network")
include(":core:testing")
include(":feature:employee")
include(":core:commonmodel")
include(":core:common")
include(":core:ui")
include(":feature:thrombinoscope")
include(":core:navigation")
include(":core:asyncimage")
include(":feature:employeedetails")
