package tl.rulate.ru.ui.search

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import tl.rulate.ru.Constants
import tl.rulate.ru.JsonData.BlogJsonData
import tl.rulate.ru.ui.novels.NovelsViewModel
import tl.rulate.ru.viewModels.BaseViewModel

object SearchViewModel : BaseViewModel() {
    var searchResults: MutableLiveData<MutableList<SearchBooksJsonData.Titles>> = MutableLiveData()
    var search: MutableLiveData<String> = MutableLiveData()

    // функция запроса данных с блога
    fun searchBooks() {
        ApiDpc.searchBooks(key = Constants.KEY, search = search.value?:"")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                searchResults.value = result.response.toMutableList()
            }, { error ->
                //todo ебануть что нибудь
//                toastMessage.value = error.message
            })
    }

}
