plugins {
    id("server.discovery.common-conventions")
    `maven-publish`
}

dependencies {
    implementation(libs.jetcd)
}

publishing {
    publications {
        create<MavenPublication>("read") {
            from(components["java"])
        }
    }

    repositories {
        maven {
            name = "GitHub"
            url = uri("https://maven.pkg.github.com/self-crafted/network-etcd")
        }
    }
}