package com.example.databinding

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.databinding.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewBinding =
            DataBindingUtil.setContentView<ActivitySecondBinding>(this, R.layout.activity_second)
        viewBinding.list = arrayListOf(
            Model.User("Name 1", "Description 1", 5),
            Model.User("Name 2", "Description 2", 10)
        )
        viewBinding.listener = clickListener
        viewBinding.executePendingBindings()
    }

    private val clickListener = object : OnItemClickListener<Model.User> {
        override fun onItemClicked(item: Model.User?, position: Int) {
            Toast.makeText(this@SecondActivity, item?.name ?: "", Toast.LENGTH_LONG).show()
        }
    }
}
