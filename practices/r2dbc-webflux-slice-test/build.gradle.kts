plugins {
    java
    id("org.springframework.boot") version "3.2.2"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "com"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.security:spring-security-core")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    // reactor tool
    implementation("io.projectreactor:reactor-tools")

    // r2dbc
    implementation("io.asyncer:r2dbc-mysql:0.9.3")

    // rxjava
    implementation("io.reactivex.rxjava3:rxjava:3.1.8")

    if (System.getProperty("os.arch") == "aarch64" && System.getProperty("os.name") == "Mac OS X") {
        runtimeOnly("io.netty:netty-resolver-dns-native-macos:4.1.100.Final:osx-aarch_64")
    }

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testAnnotationProcessor("org.projectlombok:lombok")
    testCompileOnly("org.projectlombok:lombok")

    // mockito
    testImplementation("org.mockito:mockito-core")

    // reactor test
    testImplementation("io.projectreactor:reactor-test")

    // r2dbc h2
    testImplementation("io.r2dbc:r2dbc-h2")

    // h2
    testRuntimeOnly("com.h2database:h2")

    // embedded mongo
    testImplementation("de.flapdoodle.embed:de.flapdoodle.embed.mongo:4.12.2")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
