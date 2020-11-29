package tl.rulate.ru.viewModels

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import tl.rulate.ru.SharedPrefManager

object MainViewModel : BaseViewModel() {

    // todo сделать все viewModel
    var currentFragment: MutableLiveData<Fragment> = MutableLiveData()
    var myUserId: MutableLiveData<Int> = MutableLiveData()
    var lastBookId: MutableLiveData<Int> = MutableLiveData()
    var lastChapterId: MutableLiveData<Int> = MutableLiveData()

    lateinit var sharedPref:SharedPrefManager

    init {

        Log.d("Debug", "MVVM INI")
    }

}