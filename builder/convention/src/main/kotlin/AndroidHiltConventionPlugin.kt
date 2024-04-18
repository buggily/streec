import ext.getLib
import ext.getLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.kotlin.dsl.dependencies

class AndroidHiltConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("org.jetbrains.kotlin.kapt")
            apply("dagger.hilt.android.plugin")
        }

        dependencies {
            with(getLibs()) {
                add("implementation", getLib("androidx.hilt.navigation"))
                add("implementation", getLib("androidx.hilt.navigation.fragment"))


                add("implementation", getLib("hilt.android"))
                add("kapt", getLib("hilt.android.compiler"))
            }
        }
    }
}
