plugins {
    java
    id("java-library")
    id("com.palantir.java-format-idea") version "2.38.0"
    id("org.springframework.boot") version "3.2.0"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "com.github"
version = "0.0.1"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    /* web */
    implementation("org.springframework.boot:spring-boot-starter-web")
    /* flyway */
    implementation("org.flywaydb:flyway-core")
    implementation("org.flywaydb:flyway-mysql")
    /* mybatis-flex */
    implementation("com.mybatis-flex:mybatis-flex-spring-boot-starter:1.7.5"){
        exclude("org.mybatis",  "mybatis-spring")
    }
    implementation("org.mybatis:mybatis-spring:3.0.3")
    annotationProcessor("com.mybatis-flex:mybatis-flex-processor:1.7.5")
    implementation("com.zaxxer:HikariCP:5.1.0")
    /* lombok */
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testCompileOnly("org.projectlombok:lombok")
    testAnnotationProcessor("org.projectlombok:lombok")
    /* docker-compose */
    developmentOnly("org.springframework.boot:spring-boot-docker-compose")
    /* MySQL 驱动 */
    runtimeOnly("com.mysql:mysql-connector-j")
    /* 测试 */
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.mybatis.spring.boot:mybatis-spring-boot-starter-test:3.0.3")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
