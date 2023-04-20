

plugins {
	java
	id("org.springframework.boot") version "3.1.0-SNAPSHOT"
	id("io.spring.dependency-management") version "1.1.0"
	id("org.flywaydb.flyway") version "9.8.1"
}

group = "com.hivel"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_20

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/milestone") }
	maven { url = uri("https://repo.spring.io/snapshot") }
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-jooq")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("io.springfox:springfox-boot-starter:3.0.0")
	implementation("org.flywaydb:flyway-core")
	implementation("org.springframework.kafka:spring-kafka")
	implementation("org.jetbrains:annotations:24.0.0")
	runtimeOnly("com.h2database:h2")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.kafka:spring-kafka-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

flyway {
	url = "jdbc:h2:~/data/db;AUTO_SERVER=TRUE"
	user = "sa"
	password = ""
	schemas = arrayOf("PUBLIC")
	locations = arrayOf("filesystem:${project.projectDir}/src/main/resources/db/migration")
	cleanDisabled = false
}


