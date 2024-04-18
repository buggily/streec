plugins {
    id("streec.android.library.domain")
}

android {
    namespace = "com.buggily.streec.domain.streec"
}

dependencies {
    implementation(project(":data:streec"))
}
