plugins {
    id("com.android.application")
}

android {
    namespace = "com.fj.developferjuarez"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.fj.developferjuarez"
        minSdk = 24
        targetSdk = 33
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
}

dependencies {

    //noinspection GradleCompatible
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.android.support:support-annotations:28.0.0")
    implementation("androidx.annotation:annotation:1.7.0")
    implementation("com.android.support:support-v4:28.0.0")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("com.android.support:support-v13:28.0.0")
    implementation 'at.favre.lib:bcrypt:0.11.0'

}