pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://maven.pkg.github.com/ZhumabayDias/logger")
            credentials {
                username = providers.gradleProperty("gpr.user").orNull ?: System.getenv("USERNAME")
                password = providers.gradleProperty("gpr.key").orNull ?: System.getenv("GITHUB_TOKEN")
            }
        }
    }
}

rootProject.name = "Tappy"

include(":app")
include(":domain")
include(":data")
include(":core")