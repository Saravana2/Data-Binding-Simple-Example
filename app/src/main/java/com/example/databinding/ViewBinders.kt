package com.example.databinding

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.subjects.BehaviorSubject

object ViewBinders {

    @BindingAdapter("app:changeByLikesColor")
    @JvmStatic
    fun changeByLikesColor(textView: TextView, likes:Int) {
        textView.setTextColor(
            ContextCompat.getColor(textView.context,
            when  {
                likes < 4 -> R.color.colorPrimaryDark
                likes in 4..9  -> R.color.colorPrimary
                likes > 9  -> R.color.colorAccent
                else -> R.color.colorPrimaryDark
            })
        )
        textView.text = when  {
            likes < 4 -> "Normal"
            likes in 4..9  -> "Popular"
            likes > 9  -> "Star"
            else -> ""
        }

    }

    @BindingAdapter(value = ["onClickHandler","recyclerViewAdapter"],requireAll = false)
    @JvmStatic
    fun <T> RecyclerView.setRecyclerViewAdapter(listener: OnItemClickListener<T>?,list: List<T>?){
        val adapter = MyAdapter(R.layout.item_adapter, list?: arrayListOf())
        adapter.setOnItemClickListener(listener)
        this.adapter = adapter
    }

    @BindingAdapter(value = ["onClickHandler","reactiveList"],requireAll = false)
    @JvmStatic
    fun <T> RecyclerView.setRecyclerView(listener: OnItemClickListener<T>?,list: BehaviorSubject<ArrayList<T>>?){
        val adapter = MyReactiveAdapter(R.layout.item_adapter, list?: BehaviorSubject.createDefault(arrayListOf()))
        adapter.setOnItemClickListener(listener)
        this.adapter = adapter
    }

    @BindingMethods(value = [BindingMethod(type = ImageView::class, attribute = "android:tint", method = "setImageTintList")]) class ImageViewBindingAdapters{}


    @set:BindingAdapter("visibleOrGone")
    @JvmStatic
    var View.visibleOrGone
        get() = visibility == View.VISIBLE
        set(value) {
            visibility = if (value) View.VISIBLE else View.GONE
        }

    @set:BindingAdapter("visible")
    @JvmStatic
    var View.visible
        get() = visibility == View.VISIBLE
        set(value) {
            visibility = if (value) View.VISIBLE else View.INVISIBLE
        }

}