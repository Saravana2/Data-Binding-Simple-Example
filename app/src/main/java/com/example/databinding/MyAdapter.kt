package com.example.databinding

import androidx.databinding.ViewDataBinding

class MyAdapter<T>(var layoutId:Int, var data:List<T>) : MyBaseAdapter(){
    var commonLayoutBRVariable=BR.adapterData

    private var onItemClickListener:OnItemClickListener<T>?=null

    fun setOnItemClickListener(onItemClickListener:OnItemClickListener<T>?){
        this.onItemClickListener=onItemClickListener
    }

    fun setLayoutBrVariable(varName:Int){
        this.commonLayoutBRVariable=varName
    }

    override fun bindData(position: Int, binding: ViewDataBinding) {
        binding.setVariable(commonLayoutBRVariable,data[position])
        binding.root.setOnClickListener { this.onItemClickListener?.onItemClicked(data[position],position) }
        binding.executePendingBindings()
    }

    override fun getLayoutIdByPosition(position: Int): Int {
        return layoutId
    }

    override fun getItemCount(): Int {
        return data.size
    }
}