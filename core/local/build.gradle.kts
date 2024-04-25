plugins {
    id("streec.android.library")
    id("streec.android.hilt")
}

android {
    namespace = "com.buggily.streec.core.local"
}

dependencies {
    implementation(project(":local:streec"))

    with(libs) {
        implementation(kotlinx.dateTime)

        implementation(androidx.room)
        implementation(androidx.room.runtime)
        implementation(androidx.room.paging)

        kapt(androidx.room.compiler)
    }
}
