import ext.getLib
import ext.getLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidLibraryDataConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("streec.android.library")
            apply("streec.android.hilt")
        }

        dependencies {
            add("implementation", project(":core:data"))
            add("implementation", project(":core:local"))

            with(getLibs()) {
                add("implementation", getLib("kotlinx.dateTime"))
                add("implementation", getLib("androidx.paging"))
                add("testImplementation", getLib("androidx.paging.test"))
            }
        }
    }
}
