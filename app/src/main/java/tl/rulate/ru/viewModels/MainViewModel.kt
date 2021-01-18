package tl.rulate.ru.viewModels

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import tl.rulate.ru.SharedPrefManager

object MainViewModel : BaseViewModel() {

    // todo сделать все viewModel
    var isLogoutPressed: MutableLiveData<Boolean> = MutableLiveData()
    var lastBookId: MutableLiveData<Int> = MutableLiveData(-1)
    var lastChapterId: MutableLiveData<Int> = MutableLiveData(-1)


    lateinit var sharedPref:SharedPrefManager

    // функция проверки авторизованности пользователя
    fun isUserAuthorized(): Boolean {
        if (sharedPref.user.id != -1)
            return true
        return false
    }

    fun isUserReadBook(): Boolean {
        if (sharedPref.book.lastBookId != -1)
            return true
        return false
    }

    fun isUserReadChapter(): Boolean {
        if (sharedPref.book.lastChapterId != -1)
            return true
        return false
    }

}