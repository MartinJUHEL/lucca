import java.util.Properties

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.kotlin.serialization) apply false
}

allprojects {
    // Creates environment variables from local.properties/system properties. Check buildSrc/EnvVar.
    createEnvVars()
}


// Gets environment variables
// ----------------------------------------------

/**
 * Gets the environment variables from the system environment variables, and the local.properties
 * as a fallback. So when working locally, it uses the local.properties, and the CI will
 * use its own environment variables.
 */
fun createEnvVars() {
    val fileName = "local.properties"
    val properties = Properties()
    if (rootProject.file(fileName).exists()) {
        properties.load(rootProject.file(fileName).inputStream())
    }
    for (envVar in EnvVar.values()) {
        // First, tries to read from the env vars.
        val envVarName = envVar.name
        var value = System.getenv(envVarName)
        if (value.isNullOrEmpty()) {
            // Fallback to the local property file if not present.
            value = properties.getProperty(envVarName, null)
        }
        // Sanity check.
        require(!value.isNullOrEmpty()) {
            "Property $envVarName not set in either environment variable, or local properties!"
        }

        project.ext.set(envVarName, value)
    }
}

/** @return the value of the given [envVar]. */
fun getEnvVar(envVar: EnvVar) = project.ext[envVar.name] as String