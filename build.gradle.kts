plugins {
    id("java")
    id("com.github.johnrengelman.shadow").version("7.1.0")
}

group = "com.wytheria.damager"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    compileOnly( "org.spigotmc:spigot-api:1.20.1-R0.1-SNAPSHOT")
}

tasks.test {
    useJUnitPlatform()
}

tasks.shadowJar {
    relocate("io.papermc.lib", "org.terraform.lib")
}