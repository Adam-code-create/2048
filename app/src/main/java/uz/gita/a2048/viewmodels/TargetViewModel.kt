package uz.gita.a2048.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.a2048.domain.MyRepository

class TargetViewModel :ViewModel() {
    private val repository = MyRepository.getRepository()

    private val _targetLiveData = MutableLiveData<Boolean>()
    val targetLiveData : LiveData<Boolean> get() =  _targetLiveData

    init {
        _targetLiveData.value = repository.startState
    }


    fun startGameByTarget(){
        repository.startGameByTarget()
        _targetLiveData.value = repository.startState
    }

}