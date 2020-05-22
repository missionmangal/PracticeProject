package com.myapplication.espresso_testing;

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.data.EmployeeData
import com.myapplication.R
import com.myapplication.databinding.ActivityLoginBinding
import com.myapplication.rxjava_retrofit.network.RetrofiGenerator
import com.myapplication.service_communication.ServiceActivity
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import retrofit2.Response

class LoginActivity : AppCompatActivity() , MessageDelayer.DelayerCallback {


    var  mBinding :ActivityLoginBinding ?=null
    @VisibleForTesting
    public var mIdlingResource =SimpleIdlingResource()
    get
    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_login);

        mBinding?.let {
            it.login.setOnClickListener {
                mIdlingResource.setIdleState(false)
                Toast.makeText(this@LoginActivity, "Calling Api", Toast.LENGTH_LONG).show()

               getPost()
            }
        }
    }
    var response:Response<EmployeeData>?=null
      fun getPost(){
//          var response

         MainScope().launch {
             response = RetrofiGenerator.getRequestApi()
                             .getPosts().await()
             println(response)
             Log.d("aaaaaaaaaa",response?.body().toString())
             var intent = Intent(this@LoginActivity,ServiceActivity::class.java)
             startActivity(intent)
             mIdlingResource.setIdleState(true)
//             Toast.makeText(this@LoginActivity, response?.body().toString(), Toast.LENGTH_LONG).show()
//             MessageDelayer.processMessage(response?.body().toString(), this@LoginActivity, mIdlingResource);
         }
//          MessageDelayer.processMessage(response?.body().toString(), this@LoginActivity, mIdlingResource);/

     }

    override fun onDone(text: String?) {
//        println(text)
        Log.d("aaaaaaaaaa",text)
//        Toast.makeText(this@LoginActivity, response?.body().toString(), Toast.LENGTH_LONG).show()

    }
}
