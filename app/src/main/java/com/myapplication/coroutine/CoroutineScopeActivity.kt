package com.myapplication.coroutine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.myapplication.MainActivity
import com.myapplication.R
import com.myapplication.tutorials.kotlin.avinash.extensions.startAct
import kotlinx.android.synthetic.main.activity_coroutine_scope.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class CoroutineScopeActivity : AppCompatActivity() {

    var mainScope:CoroutineScope = MainScope()
    var corScope:CoroutineScope?=null
    lateinit var tvCount :TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine_scope)
        tvCount = findViewById(R.id.tv_count)


        btn_start_count.setOnClickListener {
//            coroutineScopeFun()
//            useMainscope()
            useCoroutineScope()
        }
        btn_Next.setOnClickListener {
            startActivity(Intent(this@CoroutineScopeActivity,MainActivity::class.java))
            finish()
        }
    }


    //*********************************coroutineScope Fun Start***************************
     fun coroutineScopeFun(){
        GlobalScope.launch {
            coroutineScope {
                for (i in 1..20) {
                    delay(500)
                    tv_count.setText("Count is $i")
                }
            }
            btn_Next.setText("Completed")
        }
    }
//*********************************coroutineScope Fun End***************************


//*********************************MainScope Fun Start***************************

    private fun useMainscope() {
        mainScopeFun()
        /*lifecycleScope.launch {
            delay(5000)
            startAct(MainActivity::class.java)
            finish()
        }*/
    }
     fun mainScopeFun(){
        mainScope.launch {
                for (i in 1..20) {
                    delay(500)
                    tvCount.setText("Count is $i")
                    System.out.println("Count is $i")
                }
           btn_Next.setText("Completed")
        }
    }
//*********************************MainScope Fun End***************************

//*********************************MainScope Fun Start***************************

    private fun useCoroutineScope() {
        CorScopeFun()
        /*lifecycleScope.launch {
            delay(5000)
            startAct(MainActivity::class.java)
            finish()
        }*/
    }
     fun CorScopeFun(){
         corScope = CoroutineScope(Dispatchers.Main)
        corScope?.launch {
                for (i in 1..20) {
                    delay(500)
                    tvCount.setText("Count is $i")
                    System.out.println("Count is $i")
                }
           btn_Next.setText("Completed")
        }
    }
//*********************************MainScope Fun End***************************

    override fun onDestroy() {
        super.onDestroy()
//        mainScope.cancel()
        corScope?.cancel()
    }
}
