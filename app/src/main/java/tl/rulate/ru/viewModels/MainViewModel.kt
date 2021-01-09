package tl.rulate.ru.viewModels

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import tl.rulate.ru.SharedPrefManager

object MainViewModel : BaseViewModel() {

    // todo сделать все viewModel
    var currentFragment: MutableLiveData<Fragment> = MutableLiveData()
    var isLogoutPressed: MutableLiveData<Boolean> = MutableLiveData()

    lateinit var sharedPref:SharedPrefManager

    init {
    }

}