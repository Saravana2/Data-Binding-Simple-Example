package com.example.databinding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView


abstract class MyBaseAdapter : RecyclerView.Adapter<MyBaseAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder{
        /*Used layout id as View Type */
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            viewType,
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        bindData(position,holder.binding)
    }

    override fun getItemViewType(position: Int): Int {
        /*Used layout id as View Type */
        return getLayoutIdByPosition(position)
    }

    abstract fun bindData(position:Int,binding: ViewDataBinding)

    abstract fun getLayoutIdByPosition(position: Int): Int

      class MyViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

}