package com.buggily.skeleton

import com.android.build.api.dsl.CommonExtension
import ext.getLibs
import ext.getVersion
import org.gradle.api.Project

internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) = with(commonExtension) {

    compileSdk = 34

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = getLibs().getVersion("androidxComposeCompiler").toString()
    }
}
