package com.myapplication.espresso_testing

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.contentValuesOf
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.data.Data
import com.data.PostDataModel
import com.myapplication.BR
import com.myapplication.R
import com.myapplication.databinding.ItemPostBinding

class PostAdapter(var context:Context,var list:List<Data>?,var adapterListener:PostAdapter.AdapterListener) : RecyclerView.Adapter<PostAdapter.PostHolder>() {

    inner class PostHolder(var mBinding:ItemPostBinding ) : RecyclerView.ViewHolder( mBinding.root) {
        fun bind(item:Data){
            mBinding.setVariable(BR.item,item)
            mBinding.root.setOnClickListener {
                Log.d("aaaaaaa : Before",item.employee_name)
                Toast.makeText(context, item.employee_name,Toast.LENGTH_LONG).show()
                adapterListener.onItemClick(item.employee_name)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        var inflater = LayoutInflater.from(context);
        var binding =  DataBindingUtil.inflate<ItemPostBinding>(inflater,R.layout.item_post,parent,false)
        return PostHolder(binding)
    }

    override fun getItemCount(): Int {
        return list?.size?:0
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        list?.let { holder.bind(it.get(position))}
    }

    fun setData(list: List<Data>?) {
        list?.let {
            this.list = list
            this.notifyDataSetChanged()
        }
    }

    interface AdapterListener{
        fun onItemClick(name:String)
    }
}