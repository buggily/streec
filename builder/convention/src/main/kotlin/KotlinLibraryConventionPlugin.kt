import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.configure
import com.buggily.streec.configureKotlin
import ext.getLib
import ext.getLibs
import org.gradle.kotlin.dsl.dependencies

class KotlinLibraryConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("java-library")
            apply("org.jetbrains.kotlin.jvm")
        }

        extensions.configure<JavaPluginExtension> {
            configureKotlin(this)
        }

        dependencies {
            add("testImplementation", project(":core:test"))

            with(getLibs()) {
                add("testImplementation", getLib("junit"))
                add("testImplementation", getLib("mockk"))

                add("implementation", getLib("kotlinx.coroutines.android"))
                add("testImplementation", getLib("kotlinx.coroutines.test"))
            }
        }
    }
}
