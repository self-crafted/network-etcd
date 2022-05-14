plugins {
    `java-library`
}

tasks {
    java {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17

        withSourcesJar()
        withJavadocJar()
    }
    // If you plan to use a different file for the license, don't forget to change the file name here!
    jar {
        from("LICENSE") {
            rename { "${it}_${project.properties["archives_base_name"]!!}" }
        }
    }
}