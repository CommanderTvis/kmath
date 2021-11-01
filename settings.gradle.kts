pluginManagement {
    repositories {
        maven("https://repo.kotlin.link")
        mavenCentral()
        gradlePluginPortal()
    }

    val kotlinVersion = "1.6.0-RC"
    val toolsVersion = "0.10.5"

    plugins {
        id("org.jetbrains.kotlinx.benchmark") version "0.3.1"
        id("ru.mipt.npm.gradle.project") version toolsVersion
        id("ru.mipt.npm.gradle.jvm") version toolsVersion
        id("ru.mipt.npm.gradle.mpp") version toolsVersion
        kotlin("multiplatform") version kotlinVersion
        kotlin("plugin.allopen") version kotlinVersion
    }
}

rootProject.name = "kmath"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
enableFeaturePreview("VERSION_CATALOGS")

include(
    ":kmath-memory",
    ":kmath-complex",
    ":kmath-core",
    ":kmath-coroutines",
    ":kmath-functions",
    ":kmath-histograms",
    ":kmath-commons",
    ":kmath-viktor",
    ":kmath-multik",
    ":kmath-optimization",
    ":kmath-stat",
    ":kmath-nd4j",
    ":kmath-dimensions",
    ":kmath-for-real",
    ":kmath-geometry",
    ":kmath-ast",
    ":kmath-ejml",
    ":kmath-kotlingrad",
    ":kmath-tensors",
    ":kmath-jupyter",
    ":kmath-symja",
    ":kmath-jafama",
    ":examples",
    ":benchmarks",
)


if(System.getProperty("os.name") == "Linux"){
    include(":kmath-noa")
}
