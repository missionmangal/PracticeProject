package com.myapplication.espresso_testing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.VisibleForTesting
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.data.Data
import com.data.PostDataModel
import com.myapplication.R
import com.myapplication.databinding.ActivityListBinding
import com.myapplication.rxjava_retrofit.network.RetrofiGenerator
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ListActivity : AppCompatActivity() {

    lateinit var mBinding: ActivityListBinding
    var adapter:PostAdapter?=null
    var list:List<Data>?=null
    @VisibleForTesting
    public var mIdleResource = SimpleIdlingResource()
    get
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_list)
        mBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = PostAdapter(this,null,object :PostAdapter.AdapterListener{
            override fun onItemClick(name: String) {
                MainScope().launch {
                    mIdleResource.setIdleState(false)
                    delay(5000L)
                    Log.d("aaaaaaa : After",name)
                    Toast.makeText(this@ListActivity, name, Toast.LENGTH_LONG).show()
                    mIdleResource.setIdleState(true)
                }
            }
        })
        mBinding.recyclerView.adapter = adapter
        getPostList()
    }

    private fun getPostList() {
        mIdleResource.setIdleState(false)
        MainScope().launch {
            var response = RetrofiGenerator.getRequestApi().getPosts().await()
            if(response.isSuccessful){
                response.body()?.let { list = it.data
                setListdata()
                }
            }
            mIdleResource.setIdleState(true)
        }
    }

    private fun setListdata() {
        adapter?.setData(list)
    }

}
