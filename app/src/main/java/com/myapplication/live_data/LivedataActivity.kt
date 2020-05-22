package com.myapplication.live_data

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.myapplication.BR
import com.myapplication.R
import com.myapplication.databinding.ActivityLivedataBinding
import kotlinx.android.synthetic.main.activity_livedata.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class LivedataActivity : AppCompatActivity() {

     lateinit var viewModel:LiveDataViewModel
    lateinit var mBinding : ActivityLivedataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_livedata)

//        viewModel = ViewModelProviders.of(this).get(LiveDataViewModel::class.java)
        viewModel =ViewModelProvider(this).get(LiveDataViewModel::class.java)
        mBinding.setVariable(BR.viewModel,viewModel)
        mBinding.executePendingBindings()
        btn_sec_activity.setOnClickListener {
            var intent = Intent(this,SecondActivity::class.java)
            startActivity(intent)
        }
        btn_get_random.setOnClickListener {
            MainScope().launch {
                delay(5000)
                println("IN GLobal Launch")
                viewModel.fetchRandomNumber()

            }
        }
        viewModel.mutableRandomNumer.observe(this, Observer {
            viewModel.randomNumber.set( "${it}")
            println(viewModel.randomNumber.get())
        })
    }
}
