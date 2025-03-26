plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.martin.lucca.core.network"
    defaultConfig {
        compileSdk = libs.versions.projectCompileSdkVersion.get().toInt()
        minSdk = libs.versions.projectMinSdkVersion.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        buildConfigField("String", "API_KEY", "\"${getEnvVar(EnvVar.LUCCA_API_KEY)}\"")
        buildConfigField(
            "String",
            "BASE_URL",
            """"https://testmobile-2025-01-24.ilucca-demo.net/""""
        )
    }

    buildTypes {
        release {
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        buildConfig = true
    }

    packaging {
        resources {
            excludes += setOf(
                "META-INF/LICENSE.md",
                "META-INF/LICENSE-notice.md",
                "META-INF/LICENSE",
                "META-INF/NOTICE.md",
                "META-INF/NOTICE"
            )
        }
    }
}

/** @return the value of the given [envVar]. */
fun getEnvVar(envVar: EnvVar) = rootProject.ext[envVar.name] as String

dependencies {
    implementation(project(":core:common"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    // Hilt.
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    // Network.
    api(libs.retrofit)
    api(libs.moshi)
    api(libs.converter.moshi)
    implementation(libs.loggingInterceptor)
}