package tl.rulate.ru.ui.blog

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import tl.rulate.ru.Constants
import tl.rulate.ru.JsonData.BlogJsonData
import tl.rulate.ru.ui.novels.NovelsViewModel
import tl.rulate.ru.viewModels.BaseViewModel

object BlogViewModel : BaseViewModel() {
    var posts: MutableLiveData<MutableList<BlogJsonData.PostData>> = MutableLiveData()
    var currentFragment: MutableLiveData<Fragment> = MutableLiveData()

    init {
        currentFragment.value = BlogContentFragment()
        blog()
    }

    // функция запроса данных с блога
    fun blog() {
        ApiDpc.blog(key = Constants.KEY, limit = 10, page = 1)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                posts.value = result.response.toMutableList()
            }, { error ->
                //todo ебануть что нибудь
//                toastMessage.value = error.message
            })
    }

}
