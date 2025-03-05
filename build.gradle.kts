plugins {
    id("org.springframework.boot") version "2.7.1"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("java")
}

dependencies {

    compileOnly("org.projectlombok:lombok:1.18.24")
    annotationProcessor("org.projectlombok:lombok:1.18.24")

    testCompileOnly("org.projectlombok:lombok:1.18.24")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.24")
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")



        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation("org.springframework.boot:spring-boot-starter-web")


        runtimeOnly("com.h2database:h2")




        implementation("org.springframework.boot:spring-boot-starter-logging")


        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

        testImplementation("org.springframework.boot:spring-boot-starter-test")


    testImplementation("org.mockito:mockito-core:4.11.0")
    testImplementation("org.mockito:mockito-junit-jupiter:4.11.0")

    testImplementation("org.assertj:assertj-core:3.24.2")

    implementation("org.reflections:reflections:0.9.12")
    implementation("mysql:mysql-connector-java:8.0.22")
    implementation("org.apache.commons:commons-dbcp2:2.8.0")
    implementation("org.hibernate:hibernate-core:5.6.8.Final")

    implementation("org.springframework.boot:spring-boot-starter-logging")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.6.4") {
        exclude(group = "ch.qos.logback", module = "logback-classic")
    }
    implementation("org.springframework.boot:spring-boot-starter-web:2.7.5") {
        exclude(group = "ch.qos.logback", module = "logback-classic")
    }
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf:2.7.5") {
        exclude(group = "ch.qos.logback", module = "logback-classic")
    }
    implementation("org.springdoc:springdoc-openapi-ui:1.6.14")




    testImplementation("org.springframework:spring-test:5.3.23")
    testImplementation("com.h2database:h2:1.4.200")


    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.security:spring-security-test:5.7.3")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

repositories {
    mavenCentral()
}
tasks.test {
    useJUnitPlatform()
}