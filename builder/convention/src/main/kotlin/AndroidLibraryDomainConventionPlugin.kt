import ext.getLib
import ext.getLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidLibraryDomainConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("streec.android.library")
            apply("streec.android.hilt")

            apply("org.jetbrains.kotlin.kapt")
        }

        dependencies {
            add("implementation", project(":core:domain"))

            with(getLibs()) {
                add("implementation", getLib("kotlinx.dateTime"))
                add("implementation", getLib("androidx.paging"))
            }
        }
    }
}