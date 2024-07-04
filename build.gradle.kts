plugins {
    java
    id("org.springframework.boot") version "2.7.11" apply false
    id("io.spring.dependency-management") version "1.1.0" apply false

    kotlin("jvm") apply false
    kotlin("plugin.spring") apply false
}

subprojects {
    apply(plugin = "java")

    repositories {
        mavenCentral()
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(17))
            vendor.set(JvmVendorSpec.ADOPTIUM)
        }
    }

    dependencies {
        // lombok
        annotationProcessor("org.projectlombok:lombok:_")
        compileOnly("org.projectlombok:lombok:_")

        // test
        testImplementation("org.junit.jupiter:junit-jupiter-api:_")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:_")
    }
}
