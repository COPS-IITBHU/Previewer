package com.cops.iitbhu.previewer.lib

import android.app.Application

class Previewer {
    companion object{
        private var instance : Previewer? = null
        private var contextApplication : Application? = null
        fun getInstance() : Previewer {
            if(instance==null) instance = Previewer()
            return instance!!
        }
        fun init(contextApplication: Application) {
            this.contextApplication=contextApplication
        }
    }

}