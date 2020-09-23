package com.example.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.example.databinding.databinding.ActivityThirdBinding
import io.reactivex.subjects.BehaviorSubject


class ThirdActivity : AppCompatActivity() {

    val adapterList: BehaviorSubject<ArrayList<Model.User>> = BehaviorSubject.createDefault(arrayListOf())
    var  viewBindingGlobal: ActivityThirdBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewBinding =
            DataBindingUtil.setContentView<ActivityThirdBinding>(this, R.layout.activity_third)
        adapterList.value.addAll(arrayListOf(
            Model.User("Name 1", "Description 1", 5),
            Model.User("Name 2", "Description 2", 10)
        ))
        viewBindingGlobal=viewBinding
        val colorListTemp= ContextCompat.getColorStateList(this, R.color.colorAccent)
        viewBinding.activity = this
        viewBinding.list = adapterList
        viewBinding.apply {
            colorList = colorListTemp
            visibility = false
        }
        viewBinding.listener = clickListener
        viewBinding.lifecycleOwner = this
        viewBinding.executePendingBindings()
    }

    private val clickListener = object : OnItemClickListener<Model.User> {
        override fun onItemClicked(item: Model.User?, position: Int) {
            Toast.makeText(this@ThirdActivity, item?.name ?: "", Toast.LENGTH_LONG).show()
        }
    }

    fun onButtonClicked(){
        val list=adapterList.value.clone() as ArrayList<Model.User>
        list[0].name ="User Name1"
        list[1].name ="User Name2"
        Log.i("List",list.toString())

        adapterList.onNext(list)
        viewBindingGlobal?.apply {
            visibility = !(viewBindingGlobal?.imageView?.visibleOrGone==true)
        }
        /*adapterList.value.clear()
        adapterList.value.addAll(list)*/
    }

   companion object{
       @set:BindingAdapter("visibleOrGone")
       @JvmStatic
       var View.visibleOrGone
           get() = visibility == View.VISIBLE
           set(value) {
               visibility = if (value) View.VISIBLE else View.GONE
           }
   }



    fun <T> getBehaviourSubjectList(list: List<T>):ArrayList<BehaviorSubject<T>>{
        val list=ArrayList<BehaviorSubject<T>>()

        return list
    }

    fun <T> getBehaviourSubjectObject(data: T):BehaviorSubject<T>{
        return BehaviorSubject.createDefault(data)
    }
}
