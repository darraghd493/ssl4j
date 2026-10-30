plugins {
    id("java")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":parser")) // I've been lazy and included the instructions in the parser module

    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}
