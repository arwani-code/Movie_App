package com.moviesplash.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import leakcanary.LeakCanary


@HiltAndroidApp
open class MyApplication : Application()