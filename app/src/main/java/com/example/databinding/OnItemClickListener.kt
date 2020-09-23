package com.example.databinding

interface OnItemClickListener<T>{
    fun onItemClicked(item:T?,position:Int)
}