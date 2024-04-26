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

    //버튼 클릭 상태
    private val _buttonState = mutableStateListOf(false)
    val buttonState: List<Boolean>
        get() = _buttonState

    private val _randomNumbers = mutableStateListOf<List<Int>>()
    val randomNumbers : List<List<Int>>
        get() = _randomNumbers


    init {
        // 생성자에서 초기화
        if (_buttonState.size < 45){
            for (i in 1..45){
                _buttonState.add(false)
            }
        }
    }

    // 랜덤한 6개의 숫자를 생성하고, 제외할 숫자들이 반드시 포함되지 않은 리스트 생성
    fun generateRandomNumbers(): List<List<Int>> {

        _randomNumbers.clear()
        while (_randomNumbers.size < 5) {
            val randomNumbers = mutableListOf<Int>()
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

    fun changeButtonState(number: Int){
        _buttonState[number] = !_buttonState[number]
    }
    fun addNumber(number: Int) {
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
    private fun resetButtonState(){
        if (_buttonState.size < 45){
            for (i in 1..45){
                _buttonState.add(false)
            }
        }
    }
    fun clearNumber() {
        _excludedNumberSet.clear()
    }

}