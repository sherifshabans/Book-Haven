plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.gms.google.services)
    id ("kotlin-kapt")
}

android {
    namespace = "com.elsharif.bookhaven"
    compileSdk = 34

    defaultConfig {
        buildFeatures {
            viewBinding=true
        }
        applicationId = "com.elsharif.bookhaven"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }


}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.firebase.database)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation("com.github.barteksc:android-pdf-viewer:3.2.0-beta.1")

    implementation ("com.airbnb.android:lottie:6.1.0")
    //implementation ("com.github.bumptech.glide:glide:4.16.0")
    implementation("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0")

    implementation ("androidx.lifecycle:lifecycle-viewmodel:2.8.2")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.2")

    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.0")


    implementation ("com.squareup.picasso:picasso:2.71828")

   // Firebase Libs
   //  implementation (platform("com.google.firebase:firebase-bom:32.2.0"))
   // implementation ("com.google.firebase:firebase-analytics-ktx")
   // implementation ("com.google.firebase:firebase-database-ktx")

    implementation ("androidx.room:room-runtime:2.6.1")
    annotationProcessor ("androidx.room:room-compiler:2.6.1")
    kapt ("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")



}