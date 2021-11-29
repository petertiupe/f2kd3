plugins {
    id("dev.fritz2.fritz2-gradle") version "0.13"
}

repositories {
    mavenCentral()
}

kotlin {
    jvm()
    js(IR) {
        browser()
    }.binaries.executable()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("dev.fritz2:core:0.13")
                // see https://components.fritz2.dev/
                // implementation("dev.fritz2:components:0.13")
            }
        }
        val jvmMain by getting {
            dependencies {
            }
        }
        val jsMain by getting {
            dependencies {
                implementation(npm("d3", "7.1.1"))
                // testImplementation(kotlin("test", "1.5.31"))
                // https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-html-js
                implementation("org.jetbrains.kotlinx:kotlinx-html-js:0.7.3")
            }
        }
    }
}