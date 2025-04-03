plugins {
    id("org.springframework.boot") version "2.7.1"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("java") // Используем только Java
}

dependencies {
    // Lombok для генерации геттеров, сеттеров и других методов
    compileOnly("org.projectlombok:lombok:1.18.24")
    annotationProcessor("org.projectlombok:lombok:1.18.24")

    testCompileOnly("org.projectlombok:lombok:1.18.24")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.24")
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")

    // Spring Boot и другие основные зависимости
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    runtimeOnly("com.h2database:h2")

    implementation("org.springframework.boot:spring-boot-starter-logging")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")

    // MapStruct для маппинга объектов
    implementation("org.mapstruct:mapstruct:1.5.5.Final") // Основная зависимость MapStruct
    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final") // Зависимость для обработки аннотаций с помощью annotationProcessor

    // База данных и другие зависимости
    implementation("mysql:mysql-connector-java:8.0.22")
    implementation("org.apache.commons:commons-dbcp2:2.8.0")
    implementation("org.hibernate:hibernate-core:5.6.8.Final")
    implementation("org.springdoc:springdoc-openapi-ui:1.6.14")

    // Тестирование
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.mockito:mockito-core:4.11.0")
    testImplementation("org.mockito:mockito-junit-jupiter:4.11.0")
    testImplementation("org.assertj:assertj-core:3.24.2")

    // Без Kotlin, только Java
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.security:spring-security-test:5.7.3")
}

repositories {
    mavenCentral()
}

tasks.test {
    useJUnitPlatform()
}
