plugins {
    id("streec.android.application")
    id("streec.android.application.compose")
    id("streec.android.hilt")

    id("androidx.navigation.safeargs")
}

android {

    namespace = "com.buggily.streec"

    defaultConfig {
        applicationId = namespace

        versionCode = 1
        versionName = "1.0.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true

            proguardFiles(
                getDefaultProguardFile("proguard-android.txt"),
                "proguard-rules.pro",
            )
        }
    }

    packaging {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {
    implementation(project(":core:domain"))

    implementation(project(":domain:streec"))
    implementation(project(":feature:streecs"))

    with(libs) {
        implementation(androidx.compat)

        implementation(androidx.activity)
        implementation(androidx.fragment)

        implementation(androidx.navigation)
        implementation(androidx.navigation.fragment)

        implementation(material3)

        implementation(androidx.core)
        implementation(androidx.core.splashscreen)

        implementation(androidx.lifecycle.viewModel)
        implementation(androidx.window)
    }
}
