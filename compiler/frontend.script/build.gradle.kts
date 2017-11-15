
apply { plugin("kotlin") }

jvmTarget = "1.6"

dependencies {
    compile(project(":compiler:util"))
    compile(project(":compiler:frontend"))
    compile(projectDist(":kotlin-stdlib"))
    compile(projectDist(":kotlin-reflect"))
    compile(commonDep("org.jetbrains.kotlinx", "kotlinx-coroutines-core")) { isTransitive = false }
}

sourceSets {
    "main" { projectDefault() }
    "test" {}
}

