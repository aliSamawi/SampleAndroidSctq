import org.jetbrains.kotlin.kapt3.base.Kapt

plugins {
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinAndroidExtensions)
    id(BuildPlugins.kotlinKapt)
}

android {
    compileSdkVersion(AndroidSdk.compile)
    defaultConfig {
        applicationId = AndroidSdk.applicationId
        minSdkVersion(AndroidSdk.min)
        targetSdkVersion(AndroidSdk.target)
        versionCode = AndroidSdk.versionCode
        versionName = AndroidSdk.versionName
        testInstrumentationRunner = AndroidSdk.testInstrumentRunner
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildTypes {
        getByName("debug"){
            isDebuggable = true
            buildConfigField("String","BASE_URL","\"https://api-dot-rafiji-staging.appspot.com/customer/\"")
            buildConfigField("String","CLIENT_ID","\"LZDJSCLC30MFBOI30C4IZT3AF3VRH4BYBUHKWMW4WKIZITWD\"")
            buildConfigField("String","CLIENT_SECRET","\"4MWLQVCYGL0W1IMRGHHUEM0BXZUBHHYL3PMIMVQAY2FUR3ZT\"")
            buildConfigField("String","API_VERSION","\"20200101\"")
        }
        getByName("release") {
            isMinifyEnabled = false
            buildConfigField("String","BASE_URL","\"https://api-dot-rafiji-staging.appspot.com/customer/\"")
            buildConfigField("String","CLIENT_ID","\"LZDJSCLC30MFBOI30C4IZT3AF3VRH4BYBUHKWMW4WKIZITWD\"")
            buildConfigField("String","CLIENT_SECRET","\"4MWLQVCYGL0W1IMRGHHUEM0BXZUBHHYL3PMIMVQAY2FUR3ZT\"")
            buildConfigField("String","API_VERSION","\"20200101\"")
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}


dependencies {
    implementation(Libraries.kotlinStdLib)
    implementation(Libraries.ktxCore)
    implementation(Libraries.appCompat)
    implementation(Libraries.design)
    implementation(Libraries.constraintLayout)
    implementation(Libraries.recyclerview)
    implementation(Libraries.cardview)

    implementation(Libraries.androidxLifeCycleViewModel)

    implementation(Libraries.kotlinCoroutineCore)
    implementation(Libraries.kotlinCoroutineAndroid)

    implementation(Libraries.retrofit)
    implementation(Libraries.retrofitGsonConverter)
    implementation(Libraries.gson)
    implementation(Libraries.loggingInterceptor)

    implementation(Libraries.koin)
    implementation(Libraries.koinScope)
    implementation(Libraries.koinViewModel)

    implementation(Libraries.glide)
    kapt(Libraries.glideCompiler)

    testImplementation("junit:junit:4.12")
    androidTestImplementation("androidx.test:runner:1.3.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
//    androidTestImplementation("androidx.arch.core:core-testing:2.1.0")
//    androidTestImplementation("androidx.arch.core:core-runtime:2.1.0")
    implementation("android.arch.lifecycle:extensions:1.1.1")
    implementation ("android.arch.lifecycle:viewmodel:1.1.1")
    implementation ("android.arch.lifecycle:livedata:1.1.1")
    androidTestImplementation ("android.arch.core:core-testing:1.1.1")
    annotationProcessor ("android.arch.lifecycle:compiler:1.1.1")
    androidTestImplementation("com.squareup.okhttp3:mockwebserver:4.0.0")
}
