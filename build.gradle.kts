import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
	repositories {
		mavenCentral()
	}
}

plugins {
	kotlin("jvm") version "1.5.21" apply false
	kotlin("kapt") version "1.5.21" apply false
	kotlin("plugin.spring") version "1.5.21" apply false
	id("org.springframework.boot") version "2.5.4" apply false
}

allprojects {
	group = "ar.com.flow.platform.log"
	version = "0.0.1-SNAPSHOT"

	tasks.withType<JavaCompile> {
		sourceCompatibility = "1.8"
		targetCompatibility = "1.8"
	}

	tasks.withType<KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs = listOf("-Xjsr305=strict")
			jvmTarget = "1.8"
		}
	}
}

subprojects {
	repositories {
		mavenCentral()
	}

	val mapStructVersion = "1.5.0.Beta1"

	// https://docs.spring.io/spring-boot/docs/2.5.4/gradle-plugin/reference/htmlsingle/#managing-dependencies
	apply {
		plugin("io.spring.dependency-management")
	}
	the<io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension>().apply {
		imports {
			mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
		}
		dependencies {
			dependency("org.mapstruct:mapstruct:$mapStructVersion")
			dependency("org.mapstruct:mapstruct-processor:$mapStructVersion")
		}
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}
}

// run integration tests as part of 'check' task
// tasks.check { dependsOn(integrationTest) }