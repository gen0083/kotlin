
import java.io.File

// TODO: consider adding sources as well
// e.g. from "https://android.googlesource.com/platform/dalvik/+archive/android-5.0.0_r2/dx.tar.gz"

repositories {
    ivy {
        artifactPattern("https://dl-ssl.google.com/android/repository/[artifact]_[revision]-[classifier].[ext]")
    }
}

val buildToolsZip by configurations.creating
val default by configurations.creating

dependencies {
    buildToolsZip("google:build-tools:r21.1.1:linux@zip")
}

val destDir = File(buildDir, "libs")
val unzipDir = File(buildDir, "unzip")

val unpackDxJar by task<Copy> {
    configurations.add(default)
    afterEvaluate {
        from(zipTree(buildToolsZip.singleFile)) { include("**/dx.jar") }
    }
    into(unzipDir)
}

val copyDxJar by task<Copy> {
    configurations.add(default)
    dependsOn(unpackDxJar)
    from("$unzipDir/android-5.0/lib/dx.jar") // TODO: find out how to find the file
    into(destDir)
}

artifacts.add("default", file("$destDir/dx.jar")) {
    builtBy(copyDxJar)
}
