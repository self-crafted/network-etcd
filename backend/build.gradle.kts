plugins {
    id("server.discovery.common-conventions")
}

dependencies {
    implementation(projects.common)
    implementation(libs.jetcd)
}
