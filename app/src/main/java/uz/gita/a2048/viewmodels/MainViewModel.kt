package uz.gita.a2048.viewmodels

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber
import uz.gita.a2048.domain.MyRepository
import uz.gita.a2048.shared.SharedPref

class MainViewModel : ViewModel() {
private val repository = MyRepository.getRepository()

    private val _arrayLiveData = MutableLiveData<Array<Array<Int>>>()
    val arrayLiveData : LiveData<Array<Array<Int>>> get() =  _arrayLiveData

    private val _arrayLiveDataScore = MutableLiveData<Int>()
    val arrayLiveDataScore : LiveData<Int> get() =  _arrayLiveDataScore

    private val _arrayLiveDataMovement = MutableLiveData<Int>()
    val arrayLiveDataMovement : LiveData<Int> get() =  _arrayLiveDataMovement

    private val pref = SharedPref.getSharedPref()

    init {
        _arrayLiveData.value = repository.array

    }


    fun reload(){
        repository.reload()
        _arrayLiveData.value = repository.array

    }

    fun swipeLeft(){
        repository.swipeLeft()
        _arrayLiveData.value = repository.array
    }

    fun swipeRight(){
        repository.swipeRight()
        _arrayLiveData.value = repository.array
    }

    fun swipeUp (){
        repository.swipeUp()
        _arrayLiveData.value = repository.array
    }

    fun swipeDown (){
        repository.swipeDown()
        _arrayLiveData.value = repository.array
    }

    fun score() : Int{
        _arrayLiveDataScore.value = repository.score
        pref.score = repository.score
        return  repository.score
    }

    fun movement() : Int{
        _arrayLiveDataMovement.value = repository.movement
        pref.movement = repository.movement
        return  repository.movement
    }

}