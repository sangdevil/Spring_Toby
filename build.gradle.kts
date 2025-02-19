plugins {
    id("java")
    id("org.springframework.boot") version "3.1.2" // Spring Boot 플러그인 추가
    id("io.spring.dependency-management") version "1.1.3" // 의존성 관리 플러그인 추가
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Spring Boot Starter - 기본적인 Spring 기능 포함
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.junit.jupiter:junit-jupiter")

    // Spring Boot Starter Test - JUnit 5 및 테스트 유틸 포함
    testImplementation("org.junit.jupiter:junit-jupiter")

    // Lombok (선택 사항 - 코드 간결화)
    implementation("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

}

tasks.test {
    useJUnitPlatform()
}
