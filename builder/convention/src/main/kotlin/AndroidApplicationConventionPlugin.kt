import com.android.build.api.dsl.ApplicationExtension
import com.buggily.streec.configureKotlinAndroid
import ext.getLib
import ext.getLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidApplicationConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("com.android.application")
            apply("org.jetbrains.kotlin.android")
        }

        extensions.configure<ApplicationExtension> {
            configureKotlinAndroid(this)
            defaultConfig.targetSdk = 35
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
