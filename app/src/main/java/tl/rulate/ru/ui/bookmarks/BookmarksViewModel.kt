package tl.rulate.ru.ui.bookmarks

import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import tl.rulate.ru.Constants
import tl.rulate.ru.JsonData.BookmarksJsonData
import tl.rulate.ru.viewModels.BaseViewModel

object BookmarksViewModel : BaseViewModel() {
    var bookmarks: MutableLiveData<MutableList<BookmarksJsonData.Bookmark>> = MutableLiveData()

    // функция запроса данных с блога
    fun bookmarks(token: String = "dc165efd3675046e8cb9040bf49bf089") {
        ApiDpc.bookmarks(key = Constants.KEY, token = token)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                bookmarks.value = result.response.toMutableList()
            }, { error ->
                //todo ебануть что нибудь
//                toastMessage.value = error.message
            })
    }

}
