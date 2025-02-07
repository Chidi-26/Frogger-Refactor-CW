plugins {
    application
    id("java")
    id("org.openjfx.javafxplugin") version "0.1.0"
}

group = "com.lagocp"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":External:Game-Engine"))
    implementation("org.openjfx:javafx-media:20.0.2")
    implementation("org.openjfx:javafx-controls:20.0.2")
    implementation("org.openjfx:javafx-graphics:20.0.2")
    implementation("org.apache.commons:commons-csv:1.12.0")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.junit.vintage:junit-vintage-engine")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("com.pholser:junit-quickcheck-core:1.0")
    testImplementation("com.pholser:junit-quickcheck-generators:1.0")
    testImplementation("org.mockito:mockito-core:5.14.+")

}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass = "Main"
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

javafx {
    version = "22.0.1"
    modules = listOf("javafx.controls", "javafx.media", "javafx.fxml")

}

