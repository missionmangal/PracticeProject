package com.myapplication.util

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface

class UtilDialog {

    companion object{

        fun createDialog(context: Context, title:String, message:String , onClick:(Int,Int,DialogInterface?)->Int)
        {
            AlertDialog.Builder(context).setTitle(title)
                    .setMessage(message)
                    .setPositiveButton("Ok", object :DialogInterface.OnClickListener{
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            var result= onClick(10,20,dialog)
                            System.out.println(result)
                        }
                    }).create().show()
        }


        fun createDialogUsingInterface(context: Context,title: String,message: String, dialogAction:DialogAction){
            AlertDialog.Builder(context).setTitle(title)
                    .setMessage(message)
                    .setPositiveButton("OK", object :DialogInterface.OnClickListener{
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                           var result= dialogAction.onClick(10,20,dialog)
                            System.out.println(result)
                        }

                    })
        }


        interface DialogAction{
          fun  onClick(x:Int,y:Int,dialog:DialogInterface?):Int
        }
    }
}