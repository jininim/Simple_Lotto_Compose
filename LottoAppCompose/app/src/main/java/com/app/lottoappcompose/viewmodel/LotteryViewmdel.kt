package com.app.lottoappcompose.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class LotteryViewModel : ViewModel() {
    private val _excludedNumberList = mutableListOf<Int>()

    val excludedNumberList : List<Int>
        get() = _excludedNumberList

    fun addNumber(number : Int){
        _excludedNumberList.add(number)
    }

}