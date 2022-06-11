plugins {
    id("server.discovery.common-conventions")
}

dependencies {
    api(projects.read)
    implementation(libs.jetcd)
    compileOnly(libs.kyori)
}
