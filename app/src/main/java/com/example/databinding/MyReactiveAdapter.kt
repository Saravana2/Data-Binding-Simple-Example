package com.example.databinding


import android.util.Log
import androidx.databinding.ViewDataBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.BehaviorSubject

class MyReactiveAdapter<T>(var layoutId:Int, var data: BehaviorSubject<ArrayList<T>>) : MyBaseAdapter(){
    var commonLayoutBRVariable=BR.adapterData

    init {
        data.observeOn(AndroidSchedulers.mainThread()).subscribe {
            this.notifyDataSetChanged()
        }
    }

    private var onItemClickListener:OnItemClickListener<T>?=null

    fun setOnItemClickListener(onItemClickListener:OnItemClickListener<T>?){
        this.onItemClickListener=onItemClickListener
    }

    fun setLayoutBrVariable(varName:Int){
        this.commonLayoutBRVariable=varName
    }

    override fun bindData(position: Int, binding: ViewDataBinding) {
        Log.i("BindingAdapterView",position.toString())
        binding.setVariable(commonLayoutBRVariable,data.value[position])
        binding.root.setOnClickListener { this.onItemClickListener?.onItemClicked(data.value[position],position) }
        binding.executePendingBindings()
    }

    override fun getLayoutIdByPosition(position: Int): Int {
        return layoutId
    }

    override fun getItemCount(): Int {
        return data.value.size
    }

}