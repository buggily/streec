plugins {
    id("streec.android.library.feature")
    id("streec.android.library.compose")
}

android {
    namespace = "com.buggily.streec.feature.streecs"
}

dependencies {
    implementation(project(":domain:streec"))
}
