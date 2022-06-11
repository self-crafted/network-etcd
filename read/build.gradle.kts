plugins {
    id("server.discovery.common-conventions")
}

dependencies {
    implementation(libs.jetcd)
    compileOnly(libs.kyori)
}
