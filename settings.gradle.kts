pluginManagement {
    repositories {
        gradlePluginPortal()

        maven { setUrl("https://repo.spring.io/milestone") } // Spring milestones
        maven { setUrl("https://repo.spring.io/snapshot") } // Spring snapshots
    }

    plugins {
        id("de.fayard.refreshVersions") version "0.51.0"
    }
}

plugins {
    id("de.fayard.refreshVersions")
}

rootProject.name = "webflux"

include("instagram:article:core")
include("instagram:article:persistence")
include("instagram:article:presentation")
include("instagram:image")
include("instagram:user")
include("instagram:gateway")
