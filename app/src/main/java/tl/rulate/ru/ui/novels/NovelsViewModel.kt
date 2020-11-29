package tl.rulate.ru.ui.novels

import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import tl.rulate.ru.Constants
import tl.rulate.ru.JsonData.GetReadyJsonData
import tl.rulate.ru.viewModels.BaseViewModel

object NovelsViewModel : BaseViewModel() {
    var toastMessage: MutableLiveData<String> = MutableLiveData()
    var novels: MutableLiveData<MutableList<GetReadyJsonData.Title>> = MutableLiveData()

    init {
        var title: GetReadyJsonData.Title = GetReadyJsonData.Title(
            id = 915568,
            book_id = 96708,
            s_title = "Jiu Shen/酒神",
            t_title = "Бог Вина",
            title = "Глава 119 - Высшая власть",
            img = "https://tl.rulate.ru/i/book/17/9/18543.jpg",
            ready_date = "11-21 16:02",
            lang = "с английского на русский",
            adult = 0,
            rating = 3.7
        )
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
                var resp = result.response
                novels.value = result.response.toMutableList()
            }, { error ->
                toastMessage.value = error.message
            })
    }


}