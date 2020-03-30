package com.myapplication.aidl_ipc

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.dots.aidlclient.IRemoteProductService
import com.dots.aidlclient.Product

class ProductService : Service() {

    override fun onBind(intent: Intent): IBinder {
        return mBinder
    }

    override fun onCreate() {
        super.onCreate()
    }

    private var mBinder:IRemoteProductService.Stub = object:IRemoteProductService.Stub(){
        var  products = ArrayList<Product>()
        override fun getProduct(name: String?): Product? {
            return products.filter { it.name.equals(name) }?.get(0)
//            return name
        }

        override fun addProduct(name: String?, quantity: Int, cost: Float) {

            products.add(Product(name,quantity,cost))
        }


    }
}
