package com.cops.iitbhu.previewer.lib

import android.app.Application

object Previewer{

    private lateinit var contextApplication : Application

    fun init(contextApplication: Application) {
        this.contextApplication=contextApplication
    }

}