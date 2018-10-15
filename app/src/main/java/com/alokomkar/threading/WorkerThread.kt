package com.alokomkar.threading

import android.os.Handler
import android.os.HandlerThread

class WorkerThread : HandlerThread("LooperThread") {

    private var threadHandler: Handler

    init {
        start()
        threadHandler = Handler(looper)
    }

    fun execute( runnable: Runnable ) : WorkerThread {
        threadHandler.post( runnable )
        return this
    }
}