package com.cops.iitbhu.previewer.lib

import android.app.Application

object Previewer{

    private var contextApplication : Application? = null

    fun init(contextApplication: Application) {
        this.contextApplication=contextApplication
    }

}