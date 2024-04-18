plugins {
    id("streec.android.library.data")
}

android {
    namespace = "com.buggily.streec.data.streec"
}

dependencies {
    implementation(project(":local:streec"))
}
