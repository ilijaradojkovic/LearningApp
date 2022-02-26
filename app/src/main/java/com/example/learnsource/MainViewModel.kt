package com.example.learnsource

import android.app.Application
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class MainViewModel
@ViewModelInject
constructor(private val repository: Repository,application: Application):AndroidViewModel(application)
{

    val allDataItemMain=repository.getAllMainItems()

    fun MainItemInsert(itemMainList: ItemMainList){
        viewModelScope.launch (Dispatchers.IO){

            repository.MainItemInsert(itemMainList)
        }
    }

    fun updateMainItem(itemMainList:ItemMainList){viewModelScope.launch(Dispatchers.IO) {
        repository.updateMainItem(itemMainList)
    }
    }






}