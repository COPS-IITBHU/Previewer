package com.cops.iitbhu.previewer

import android.app.Application
import com.cops.iitbhu.previewer.lib.Previewer


class DemoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Previewer.init(this)
    }
}