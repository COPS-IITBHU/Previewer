package com.cops.iitbhu.previewer

import android.app.Activity
import com.cops.iitbhu.previewer.lib.Previewer


class Application : Activity() {
    fun callInit() {
        Previewer.init(application)
    }
}