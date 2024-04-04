import java.util.Properties

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.dagger.hilt.android)
    id("kotlin-kapt")
    id("androidx.navigation.safeargs")
}

android {
    namespace = "jk.labs.dev.betsson.group.technical.assesment"
    compileSdk = 34

    defaultConfig {
        applicationId = "jk.labs.dev.betsson.group.technical.assesment"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        val properties = Properties()
        properties.load(project.rootProject.file("local.properties").inputStream())
        buildConfigField("String", "FSQ_BASE_URL", "\"${properties.getProperty("FSQ_BASE_URL")}\"")
        buildConfigField("String", "FSQ_API_KEY", "\"${properties.getProperty("FSQ_API_KEY")}\"")
        buildConfigField("Integer", "FSQ_LIMIT", properties.getProperty("FSQ_LIMIT"))
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

    flavorDimensions += "version"
    productFlavors {
        val properties = Properties()
        properties.load(project.rootProject.file("local.properties").inputStream())

        create("coffeeBars") {
            buildConfigField("Integer", "FSQ_RADIUS", properties.getProperty("FSQ_COFFEE_BAR_RADIUS"))
        }
        create("cocktailBars") {
            buildConfigField("Integer", "FSQ_RADIUS", properties.getProperty("FSQ_COCKTAIL_BAR_RADIUS"))
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        buildConfig = true
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // Glide
    implementation (libs.glide)

    //Hilt
    implementation(libs.dagger.hilt.android)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    kapt(libs.dagger.hilt.android.compiler)

    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    //Navigation
    implementation(libs.androidx.navigation.fragment.ktx.v253)
    implementation(libs.androidx.navigation.ui.ktx.v253)

    // Location
    implementation(libs.play.services.location)

    //Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    kapt(libs.androidx.room.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}