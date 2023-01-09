package com.example.finance.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GalleryViewModel : ViewModel() {
    private var realtimedata = MutableLiveData<String>()
    private var _user ="none"
    val user: String
        get() = _user

    fun update(data: String){
        realtimedata.value = data
    }
    fun loadData():LiveData<String>{
        return realtimedata
    }

    val text: LiveData<String> = loadData()
}