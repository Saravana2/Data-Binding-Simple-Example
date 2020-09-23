package com.example.databinding

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData

object Model {
    data class User(var name: String, var description: String, var _likes: Int) : BaseObservable() {

        var likes: Int
            @Bindable
            get() = _likes
            set(value) {
                _likes = value
                notifyPropertyChanged(BR.likes)
            }


        val isActiveUser : MutableLiveData<Boolean> by lazy {
            MutableLiveData<Boolean>(false)
        }

        val strText : MutableLiveData<String> by lazy {
            MutableLiveData<String>("")
        }


        fun addLike() {
            likes++
        }

        fun onPrintClicked(){
            print("User"+isActiveUser.value)
        }

    }
}