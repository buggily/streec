import ext.getLib
import ext.getLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidLibraryFeatureConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("streec.android.library")
            apply("streec.android.hilt")

            apply("org.jetbrains.kotlin.kapt")
            apply("androidx.navigation.safeargs")
        }

        dependencies {
            add("implementation", project(":core:ui"))
            add("implementation", project(":core:domain"))

            with(getLibs()) {
                add("implementation", getLib("kotlinx.dateTime"))
                add("implementation", getLib("androidx.paging"))

                add("implementation", getLib("androidx.fragment"))
                add("implementation", getLib("androidx.navigation"))
                add("implementation", getLib("androidx.navigation.fragment"))
                add("implementation", getLib("androidx.lifecycle.viewModel"))
            }
        }
    }
}
