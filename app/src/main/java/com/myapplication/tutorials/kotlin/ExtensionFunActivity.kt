package com.myapplication.tutorials.kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.myapplication.R
import com.myapplication.util.setImage
import kotlinx.android.synthetic.main.activity_extension_fun.*

class ExtensionFunActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_extension_fun)

        var url ="https://newevolutiondesigns.com/images/freebies/cool-wallpaper-3.jpg"
        Glide.get(this).setImage(url,this,img_)
    }
}
