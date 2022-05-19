plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.microsoft.bot:bot-integration-spring:4.14.2")
    implementation("org.springframework.boot:spring-boot:2.6.7")
    implementation("org.springframework.boot:spring-boot-starter-web:2.6.7")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}