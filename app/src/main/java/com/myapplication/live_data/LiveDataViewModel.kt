package com.myapplication.live_data

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.myapplication.BR
import kotlin.random.Random

class LiveDataViewModel : ViewModel() {

    var mutableRandomNumer:MutableLiveData<String>

    var randomNumber: ObservableField<String>
    init {
        randomNumber = ObservableField()
        mutableRandomNumer = MutableLiveData()
    }

    fun fetchRandomNumber(){
        var random = Random.nextInt(100)
        mutableRandomNumer.postValue(""+random)
//        mutableRandomNumer.value = "$random"
    }
}