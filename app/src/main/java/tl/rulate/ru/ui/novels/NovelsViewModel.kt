package tl.rulate.ru.ui.novels

import Title
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import tl.rulate.ru.Constants
import tl.rulate.ru.JsonData.GetReadyJsonData
import tl.rulate.ru.viewModels.BaseViewModel

object NovelsViewModel : BaseViewModel() {
    var toastMessage: MutableLiveData<String> = MutableLiveData()
    var novels: MutableLiveData<MutableList<GetReadyJsonData.NovelChapter>> = MutableLiveData()
    var newBooks: MutableLiveData<MutableList<Title>> = MutableLiveData()
    var ads: MutableLiveData<MutableList<Title>> = MutableLiveData()

    var currentFragment: MutableLiveData<Fragment> = MutableLiveData()
    var lastBookId: MutableLiveData<Int> = MutableLiveData()
    var lastChapterId: MutableLiveData<Int> = MutableLiveData()

    init {
        currentFragment.value = NovelsContentFragment()
//        novels.value = mutableListOf(title)
    }


    fun getReady(limit: Int = 10) {
        ApiDpc.getReady(
            key = Constants.KEY,
            limit = limit
        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                novels.value = result.response.toMutableList()
            }, { error ->
                toastMessage.value = error.message
            })
    }

    fun newBooks() {
        ApiDpc.newBooks(
            key = Constants.KEY
        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                newBooks.value = result.response.toMutableList()
            }, { error ->
                toastMessage.value = error.message
            })
    }

    fun ads() {
        ApiDpc.ads(
            key = Constants.KEY
        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                ads.value = result.response.toMutableList()
            }, { error ->
                toastMessage.value = error.message
            })
    }

    fun book(bookId: Int) {
        ApiDpc.book(
            key = Constants.KEY,
            book_id = bookId,
            token = "123"
        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
//                ads.value = result.response.toMutableList()
            }, { error ->
                toastMessage.value = error.message
            })
    }

}