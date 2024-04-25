import ext.getLib
import ext.getLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class KotlinLibraryLocalConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("streec.kotlin.library")
            apply("streec.kotlin.hilt")
        }

        dependencies {
            with(getLibs()) {
                add("implementation", getLib("kotlinx.dateTime"))
                add("implementation", getLib("kotlinx.coroutines.core"))

                add("implementation", getLib("androidx.room.core"))
                add("implementation", getLib("androidx.paging.core"))
            }
        }
    }
}
