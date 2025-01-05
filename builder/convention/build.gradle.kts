plugins {
    `kotlin-dsl`
}

group = "com.buggily.streec.builder"

java {
    toolchain { languageVersion.set(JavaLanguageVersion.of(17)) }
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("androidApplicationCompose") {
            id = "streec.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidApplication") {
            id = "streec.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidHilt") {
            id = "streec.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "streec.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidLibraryData") {
            id = "streec.android.library.data"
            implementationClass = "AndroidLibraryDataConventionPlugin"
        }
        register("androidLibraryDomain") {
            id = "streec.android.library.domain"
            implementationClass = "AndroidLibraryDomainConventionPlugin"
        }
        register("androidLibraryFeature") {
            id = "streec.android.library.feature"
            implementationClass = "AndroidLibraryFeatureConventionPlugin"
        }
        register("androidLibrary") {
            id = "streec.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidTest") {
            id = "streec.android.test"
            implementationClass = "AndroidTestConventionPlugin"
        }
        register("kotlinHilt") {
            id = "streec.kotlin.hilt"
            implementationClass = "KotlinHiltConventionPlugin"
        }
        register("kotlinLibrary") {
            id = "streec.kotlin.library"
            implementationClass = "KotlinLibraryConventionPlugin"
        }
        register("kotlinLibraryData") {
            id = "streec.kotlin.library.data"
            implementationClass = "KotlinLibraryDataConventionPlugin"
        }
        register("kotlinLibraryLocal") {
            id = "streec.kotlin.library.local"
            implementationClass = "KotlinLibraryLocalConventionPlugin"
        }
        register("kotlinTest") {
            id = "streec.kotlin.test"
            implementationClass = "KotlinTestConventionPlugin"
        }
    }
}
