plugins {
    kotlin("jvm") version "2.0.0"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "nl.chimpgamer"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()

    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://libraries.minecraft.net/")
}

dependencies {
    implementation(kotlin("stdlib"))
    compileOnly("net.md-5:bungeecord-api:1.20-R0.2-SNAPSHOT")

    implementation("net.kyori:adventure-api:4.17.0")
    implementation("net.kyori:adventure-platform-bungeecord:4.3.3")
    implementation("net.kyori:adventure-text-minimessage:4.17.0")
    implementation("net.kyori:adventure-text-feature-pagination:4.0.0-SNAPSHOT")
}

kotlin {
    jvmToolchain(17)
}

tasks {
    processResources {
        filesMatching("**/*.yml") {
            expand("version" to project.version)
        }
    }

    build {
        dependsOn(shadowJar)
    }
}