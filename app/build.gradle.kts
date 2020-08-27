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
            buildConfigField("String","BASE_URL","\"https://api.foursquare.com/\"")
            buildConfigField("String","CLIENT_ID","\"LZDJSCLC30MFBOI30C4IZT3AF3VRH4BYBUHKWMW4WKIZITWD\"")
            buildConfigField("String","CLIENT_SECRET","\"4MWLQVCYGL0W1IMRGHHUEM0BXZUBHHYL3PMIMVQAY2FUR3ZT\"")
            buildConfigField("String","API_VERSION","\"20200101\"")
        }
        getByName("release") {
            isMinifyEnabled = false
            buildConfigField("String","BASE_URL","\"https://api.foursquare.com/\"")
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
}
