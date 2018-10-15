package com.alokomkar.threading

import android.util.Log
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.atomic.AtomicBoolean

class SimpleWorkerThread() : Thread("SimpleWorkerThread") {

    private val tag = SimpleWorkerThread::class.java.simpleName
    private val concurrentLinkedQueue = ConcurrentLinkedQueue<Runnable>()
    private val isAlive = AtomicBoolean( true )

    init {
        start()
    }

    fun execute( runnable: Runnable ) : SimpleWorkerThread {
        concurrentLinkedQueue.add( runnable )
        return this
    }

    fun quit() {
        isAlive.set(false)
    }

    override fun run() {

        while ( isAlive.get() ) {
            concurrentLinkedQueue.poll()?.run()
        }

        Log.d(tag, "SimpleWorkerThread terminated")
    }

}