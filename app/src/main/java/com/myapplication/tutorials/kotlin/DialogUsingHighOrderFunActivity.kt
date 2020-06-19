package com.myapplication.tutorials.kotlin

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.myapplication.R
import com.myapplication.tutorials.kotlin.avinash.PracticeActivity
import com.myapplication.util.UtilDialog
import kotlinx.android.synthetic.main.activity_dialog_using_high_order_fun.*

class DialogUsingHighOrderFunActivity : AppCompatActivity() ,UtilDialog.Companion.DialogAction{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog_using_high_order_fun)

        btn_open_dialog.setOnClickListener {
            UtilDialog.createDialog(this@DialogUsingHighOrderFunActivity, "Awesome Dialog", "Hello world",
                    { x, y, dialog ->
                        dialog?.dismiss()
                        x + y
                    }
            )


        }


        btn_open_dialog.setOnClickListener {
            UtilDialog.createDialogUsingInterface(this@DialogUsingHighOrderFunActivity,
                    "Awesome Dialog",
                    "Hello world",
                  this@DialogUsingHighOrderFunActivity

            )
        }


    }

    override fun onClick(x: Int, y: Int, dialog: DialogInterface?): Int {
        dialog?.dismiss()
        return x + y
    }

    fun goToAvinashWork(view: View) {
        startActivity(Intent(this@DialogUsingHighOrderFunActivity,PracticeActivity::class.java))
    }


}
