package tl.rulate.ru.ui.indepentUi


import android.util.Log
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import tl.rulate.ru.Constants
import tl.rulate.ru.viewModels.BaseViewModel

object ReaderViewModel : BaseViewModel() {
    var toastMessage: MutableLiveData<String> = MutableLiveData()
    var text: MutableLiveData<String> = MutableLiveData()

    fun chapter(chapter_id: Int) {
        ApiDpc.chapter(
            key = Constants.KEY,
            chapter_id = chapter_id,
            token = ""

        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->

                // тостер
                toastMessage.value = result.msg

                // если ответ не нулевой то выполняется действия
                if (result.status == "success") {
                    Log.d("getBook", "${result.response.text} ")
                    text.value = result.response.text

                } else {
                    text.value = result.msg
                }
            }, { error ->
                Log.d("getBookError", "${error.message} ")

                toastMessage.value = error.message
            })
    }
}