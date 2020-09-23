package com.example.databinding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.databinding.databinding.ActivityFirstBinding

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityFirstBinding>(this, R.layout.activity_first)
        binding.activity = this
    }

    fun onVariableBindingClicked(){
        startActivity(Intent(this,MainActivity::class.java))
    }

    fun onRecyclerViewClicked(){
        startActivity(Intent(this,SecondActivity::class.java))
    }

    fun onReactiveRecyclerViewClicked(){
        startActivity(Intent(this,ThirdActivity::class.java))
    }
}