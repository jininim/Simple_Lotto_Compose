package com.app.lottoappcompose.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel

class LotteryViewModel : ViewModel() {
    //제외 수
    private val _excludedNumberSet = mutableSetOf<Int>()
    val excludedNumberSet: Set<Int>
        get() = _excludedNumberSet

    private val _fixNumberSet = mutableSetOf<Int>()
    val fixNumberSet: Set<Int>
        get() = _fixNumberSet

    //버튼 클릭 상태
    private val _exButtonState = mutableStateListOf(false)
    val exButtonState: List<Boolean>
        get() = _exButtonState

    private val _fixButtonState = mutableStateListOf(false)
    val fixButtonState: List<Boolean>
        get() = _fixButtonState

    private val _randomNumbers = mutableStateListOf<List<Int>>()
    val randomNumbers : List<List<Int>>
        get() = _randomNumbers


    init {
        // 생성자에서 초기화
        if (_exButtonState.size < 45){
            for (i in 1..45){
                _exButtonState.add(false)
            }
        }
        // 생성자에서 초기화
        if (_fixButtonState.size < 45){
            for (i in 1..45){
                _fixButtonState.add(false)
            }
        }
    }

    // 랜덤한 6개의 숫자를 생성하고, 제외할 숫자들이 반드시 포함되지 않은 리스트 생성
    fun generateRandomNumbers(): List<List<Int>> {

        _randomNumbers.clear()

        while (_randomNumbers.size < 5) {
            val randomNumbers = mutableListOf<Int>()
            for (data in _fixNumberSet){
                randomNumbers.add(data)
            }
            while (randomNumbers.size < 6) {
                val randomNumber = (1..45).random()
                if (!_excludedNumberSet.contains(randomNumber)) {
                    randomNumbers.add(randomNumber)
                }
            }
            _randomNumbers.add(randomNumbers.sorted())
        }
        return _randomNumbers
    }

    fun changeExButtonState(number: Int){
        _exButtonState[number] = !_exButtonState[number]
    }
    fun changeFixButtonState(number: Int){
        _fixButtonState[number] = !_fixButtonState[number]
    }
    fun addExcludedNumber(number: Int) {
        if (_excludedNumberSet.size < 5) {
            //excludedNumberSet의 size 가 5가 이하일 경우(제외수는 최대 5개로 설정)
            if (_excludedNumberSet.contains(number)) {
                //excludedNumberSet에 이미 고정수가 포함되어 있다면 삭제
                _excludedNumberSet.remove(number)
            } else {
                _excludedNumberSet.add(number)
            }
        }
    }

    fun addFixNumber(number: Int) {
        if (_fixNumberSet.size < 5) {
            //excludedNumberSet의 size 가 5가 이하일 경우(제외수는 최대 5개로 설정)
            if (_fixNumberSet.contains(number)) {
                //excludedNumberSet에 이미 고정수가 포함되어 있다면 삭제
                _fixNumberSet.remove(number)
            } else {
                _fixNumberSet.add(number)
            }
        }
    }


}