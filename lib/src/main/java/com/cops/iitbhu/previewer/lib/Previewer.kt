package com.cops.iitbhu.previewer.lib

class Previewer {
    companion object{
        private var instance : Previewer? = null

        fun getInstance() : Previewer {
            if(instance==null) instance = Previewer()
            return instance!!
        }
    }

    fun printHello() {
        println("Hello World")
    }
}