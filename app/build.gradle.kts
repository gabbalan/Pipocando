plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt") // Room
}

android {
    namespace = "com.example.pipocando_oficial"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.pipocando_oficial"
        minSdk = 25
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // Substitua pela sua chave real (pegue em http://www.omdbapi.com/apikey.aspx)
        buildConfigField("String", "OMDB_API_KEY", "\"SUA_OMDB_KEY_AQUI\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            // nada especial por enquanto
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
        compose = true
        buildConfig = true
    }
}

dependencies {
    // --- Base Compose/AndroidX ---
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    // --- Navegação Compose ---
    implementation(libs.androidx.navigation.compose)

    // --- Coroutines ---
    implementation(libs.kotlinx.coroutines.android)

    // --- Room ---
    implementation(libs.room.ktx)
    kapt(libs.room.compiler)

    // --- Retrofit + Gson ---
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)

    // --- Coil (imagens) ---
    implementation(libs.coil.compose)

    // --- Ícones Material (Icons.Default.*) ---
    implementation(libs.androidx.material.icons.extended)

    // --- (Opcional) helpers lifecycle para Compose (LaunchedEffect + lifecycleOwner, etc.) ---
    implementation(libs.androidx.lifecycle.runtime.compose)

    // --- Testes ---
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    // --- Debug ---
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
