plugins {
    kotlin("multiplatform")
    id("ru.mipt.npm.gradle.common")
    id("ru.mipt.npm.gradle.native")
}

kotlin.sourceSets {
    all {
        languageSettings.optIn("space.kscience.kmath.misc.UnstableKMathAPI")
    }

    filter { it.name.contains("test", true) }
        .map(org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet::languageSettings)
        .forEach { it.optIn("space.kscience.kmath.misc.PerformancePitfall") }

    commonMain {
        dependencies {
            api(project(":kmath-core"))
            implementation(project(":kmath-stat"))
        }
    }
}

readme {
    maturity = ru.mipt.npm.gradle.Maturity.PROTOTYPE
    propertyByTemplate("artifact", rootProject.file("docs/templates/ARTIFACT-TEMPLATE.md"))

    feature(
        id = "tensor algebra",
        ref = "src/commonMain/kotlin/space/kscience/kmath/tensors/api/TensorAlgebra.kt"
    ) { "Basic linear algebra operations on tensors (plus, dot, etc.)" }

    feature(
        id = "tensor algebra with broadcasting",
        ref = "src/commonMain/kotlin/space/kscience/kmath/tensors/core/BroadcastDoubleTensorAlgebra.kt"
    ) { "Basic linear algebra operations implemented with broadcasting." }

    feature(
        id = "linear algebra operations",
        ref = "src/commonMain/kotlin/space/kscience/kmath/tensors/api/LinearOpsTensorAlgebra.kt"
    ) { "Advanced linear algebra operations like LU decomposition, SVD, etc." }
}
