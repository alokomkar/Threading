package com.alokomkar.threading

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val simpleWorkerThread = SimpleWorkerThread()
    private val mainHandler = Handler(Looper.getMainLooper(), object : Handler.Callback {
        override fun handleMessage(message: Message?): Boolean {
            if( message != null ) {
                tvTaskUpdate.text = message.obj as String
            }
            return true
        }

    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        simpleWorkerThreadAction()



    }

    // https://www.youtube.com/watch?v=Yo3VT-fZr68&list=PL6nth5sRD25hVezlyqlBO9dafKMc5fAU2&index=2
    private fun simpleWorkerThreadAction() {
        simpleWorkerThread.execute(Runnable {
            try {
                Thread.sleep(1000)
            } catch ( e : InterruptedException ) {
                e.printStackTrace()
            }
            val message = Message.obtain()
            message.obj = "Task - 1 complete"
            mainHandler.sendMessage( message )
        }).execute(Runnable {
            try {
                Thread.sleep(1000)
            } catch ( e : InterruptedException ) {
                e.printStackTrace()
            }
            val message = Message.obtain()
            message.obj = "Task - 2 complete"
            mainHandler.sendMessage( message )
        }).execute(Runnable {
            try {
                Thread.sleep(1000)
            } catch ( e : InterruptedException ) {
                e.printStackTrace()
            }
            val message = Message.obtain()
            message.obj = "Task - 3 complete"
            mainHandler.sendMessage( message )
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        simpleWorkerThread.quit()
    }
}
