import com.google.protobuf.gradle.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.4.4"
	kotlin("jvm") version "1.4.30"
	kotlin("plugin.spring") version "1.4.30"
	id("com.google.protobuf") version "0.8.8"
	id("idea")
	id("java")
}

group = "jp.co.emperor.penguin"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

//sourceSets{
//	create("sample"){
//		proto {
//			srcDir("src/main/kotlin/protobuf")
//		}
//	}
//}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
//	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation(platform(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES))

	// GRPC アプリケーションを構築するためのライブラリ
	implementation("com.google.protobuf:protobuf-java:3.6.1")
	implementation("io.grpc:grpc-stub:1.15.1")
	implementation("io.grpc:grpc-protobuf:1.15.1")
	implementation("io.github.lognet:grpc-spring-boot-starter:4.4.5")

//	implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:2.1.4")
	testImplementation("org.springframework.boot:spring-boot-starter-test")

	// Promeheus
//	implementation("org.springframework.boot:spring-boot-actuator")
	implementation("io.micrometer:micrometer-registry-prometheus:1.6.6")
	implementation("org.springframework.boot:spring-boot-starter-actuator:2.4.5")
}

val springBootVersion = "2.5.2"
configurations {
	all {
		resolutionStrategy.eachDependency {
			if (requested.group == "org.springframework.boot") {
				useVersion(springBootVersion)
			}
		}
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

protobuf {
	protoc {
		artifact = "com.google.protobuf:protoc:3.6.1"
	}
	plugins{
		id("grpc"){
			artifact = "io.grpc:protoc-gen-grpc-java:1.15.1"
		}
	}
	generateProtoTasks{
		ofSourceSet("main").forEach{
			it.plugins{
				id("grpc")
			}
		}
	}
}