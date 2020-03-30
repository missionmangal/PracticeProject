package com.myapplication.aidl_ipc

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dots.aidlclient.IRemoteProductService
import com.myapplication.R


class AIDL_IPC_Activity : AppCompatActivity() {

     var service:IRemoteProductService?=null
    private var serviceConnecion: RemoteServiceConnection?= null
    private var context: Context =this;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aidl_ipc)
        connectService()
        val addProduct: Button = findViewById(R.id.btnAdd) as Button
        addProduct.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                try {
                    if (service != null) {
                        val name = (findViewById(R.id.edtName) as EditText).text.toString()
                        val quatity = (findViewById(R.id.edtQuantity) as EditText).text.toString().toInt()
                        val cost = (findViewById(R.id.edtCost) as EditText).text.toString().toFloat()
                        service!!.addProduct(name, quatity, cost)
                        Toast.makeText(this@AIDL_IPC_Activity, "Product added.", Toast.LENGTH_LONG)
                                .show()
                    } else {
                        Toast.makeText(this@AIDL_IPC_Activity, "Service is not connected", Toast.LENGTH_LONG)
                                .show()
                        connectService()
                    }
                } catch (e: Exception) {
                }
            }
        })

        val searchProduct: Button = findViewById(R.id.btnSearch) as Button
        searchProduct.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                try {
                    if (service != null) {
                        val name = (findViewById(R.id.edtSearchProduct) as EditText).text.toString()
                        val product = service!!.getProduct(name)
                        if (product != null) {
                            (findViewById(R.id.txtSearchResult) as TextView).text = product.toString()
                        } else {
                            Toast.makeText(this@AIDL_IPC_Activity, "No product found with this name", Toast.LENGTH_LONG)
                                    .show()
                        }
                    } else {
                        Toast.makeText(this@AIDL_IPC_Activity, "Service is not connected", Toast.LENGTH_LONG)
                                .show()
                    }
                } catch (e: Exception) {
                }
            }
        })
    }

    fun connectService(){
        serviceConnecion = RemoteServiceConnection()
        var intent = Intent("com.dots.aidlservice.ProductService")
        intent.setPackage("com.dots.aidlservice")
//        convertImp
        var ret = bindService(intent,serviceConnecion!!,Context.BIND_AUTO_CREATE)
    }

    inner class RemoteServiceConnection : ServiceConnection{
        override fun onServiceDisconnected(name: ComponentName?) {
            service = null
            Toast.makeText(context, "Service Disconnected", Toast.LENGTH_LONG)
                    .show();
        }

        override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
            service = IRemoteProductService.Stub.asInterface(binder )
            Toast.makeText(context, "Service connected", Toast.LENGTH_LONG)
                    .show();
        }
    }
}
