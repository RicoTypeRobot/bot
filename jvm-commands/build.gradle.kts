plugins {
    kotlin("jvm")
}

dependencies {
    implementation(platform(project(":platform")))

    implementation(kotlin("stdlib-jdk8"))
    implementation("com.jessecorbett:diskord-bot")
}
