plugins {
    id("space.kscience.gradle.jvm")
    kotlin("jupyter.api")
}

dependencies {
    api(spclibs.kotlinx.html)
    api(project(":kmath-ast"))
    api(project(":kmath-complex"))
    api(project(":kmath-for-real"))
}

readme {
    maturity = space.kscience.gradle.Maturity.PROTOTYPE
}

kotlin.sourceSets.all {
    languageSettings.optIn("space.kscience.kmath.misc.UnstableKMathAPI")
}

tasks.processJupyterApiResources {
    libraryProducers = listOf("space.kscience.kmath.jupyter.KMathJupyter")
}
