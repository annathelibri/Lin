plugins {
    kotlin("multiplatform") version "1.5.31"
}

group = "net.notjustanna"
version = "0.0.1"

repositories {
    mavenCentral()
    mavenLocal()
    maven { url = uri("https://maven.cafeteria.dev") }
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "13"
        }
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }
    js(IR) {
        browser()
        nodejs()
    }

    /*
     * The targets linuxArm64 and macosArm64 are disabled due to Okio.
     * See: https://github.com/square/okio/issues/1006
     *      https://github.com/Kotlin/kotlinx-datetime/issues/75
     *      https://youtrack.jetbrains.com/issue/KT-43996
     *
     * JetBrains pls fix your CI >.>
     */

    linuxX64()
    // linuxArm64()
    macosX64()
    // macosArm64()
    mingwX64()

    sourceSets {
        all { languageSettings.optIn("kotlin.RequiresOptIn") }
        val commonMain by getting {
            dependencies {
                implementation("net.notjustanna:tartar:3.0.2")
                implementation("com.squareup.okio:okio:3.0.0")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation("net.notjustanna:kotlin-unified-platform:1.3")
            }
        }
        val jvmMain by getting
        val jvmTest by getting
        val jsMain by getting
        val jsTest by getting
        val nativeMain by creating {
            dependsOn(commonMain)
        }
        val nativeTest by creating {
            dependsOn(nativeMain)
        }
        val linuxX64Main by getting {
            dependsOn(nativeMain)
        }
        val linuxX64Test by getting {
            dependsOn(nativeTest)
        }
        // val linuxArm64Main by getting {
        //     dependsOn(nativeMain)
        // }
        // val linuxArm64Test by getting {
        //     dependsOn(nativeTest)
        // }
        val mingwX64Main by getting {
            dependsOn(nativeMain)
        }
        val mingwX64Test by getting {
            dependsOn(nativeTest)
        }
        val macosX64Main by getting {
            dependsOn(nativeMain)
        }
        val macosX64Test by getting {
            dependsOn(nativeTest)
        }
        // val macosArm64Main by getting {
        //     dependsOn(nativeMain)
        // }
        // val macosArm64Test by getting {
        //     dependsOn(nativeTest)
        // }
    }
}
