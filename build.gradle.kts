import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
    id("org.jetbrains.intellij") version "0.4.8"
    id("application")
    //id("org.openjfx.javafxplugin") version "0.0.7"
    java
    kotlin("jvm") version "1.3.21"
}

group = "me.kwatra"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
//
dependencies {
    implementation(kotlin("stdlib-jdk8"))
    testCompile("junit", "junit", "4.12")

}
/*
javafx {
    version = "11"
    setModules(listOf("javafx.controls", "javafx.fxml"))
}*/


// See https://github.com/JetBrains/gradle-intellij-plugin/
intellij {
    version = "2019.1.2"
}
configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
tasks.getByName<org.jetbrains.intellij.tasks.PatchPluginXmlTask>("patchPluginXml") {
    changeNotes(
        """
      Add change notes here.<br>
      <em>most HTML tags may be used</em>"""
    )
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}