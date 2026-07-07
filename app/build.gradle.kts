plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    // Add the Google services Gradle plugin
    id("com.google.gms.google-services")
}

android {
    namespace = "ac.pmdm.rickandmortypf"
    compileSdk = 36

    defaultConfig {
        applicationId = "ac.pmdm.rickandmortypf"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
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
        viewBinding=true
    }
}

dependencies {
    // Retrofit principal
    implementation(libs.retrofit)
    // Convertidor Gson ( conversion de Json )
    implementation(libs.converter.gson)
    // Como vamos a usar ViewModels ( a modo secretario ) necesitamos anadir
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    // y para poder usar las imagenes a partir de una url y pintarla en nuestro
    // control grafico iu
    implementation(libs.coil)

    // para SharedPreferences
    implementation("androidx.preference:preference:1.2.0")

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:32.8.0"))
    implementation("com.firebaseui:firebase-ui-auth:9.1.1")

    // Conecta la aplicación con una base de datos en tiempo real, estructurada en formato JSON (pag 86 pdf)
    implementation("com.google.firebase:firebase-database:20.3.0")
    implementation("com.google.firebase:firebase-firestore-ktx:24.11.0")//

    // DPENDENCIA DE corrutinas..
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.6.4")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}