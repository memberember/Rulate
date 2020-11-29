package tl.rulate.ru.viewModels


import android.util.Log
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import tl.rulate.ru.Constants
import tl.rulate.ru.data.DataUser

object ReaderViewModel : BaseViewModel() {
    var mainViewModel = MainViewModel
    var toastMessage: MutableLiveData<String> = MutableLiveData()
    var text: MutableLiveData<String> = MutableLiveData()

    fun getBook(chapter_id: Int,book_id: Int) {
        ApiDpc.chapter(
            key = Constants.KEY,
            book_id = book_id,
            chapter_id = chapter_id,
            token = ""

        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->

                // тостер
                toastMessage.value = result.msg

                Log.d("getBook","${result.response.text}${result.response.text} ")
                text.value = result.response.text
                // если ответ не нулевой то выполняется действия


            }, { error ->
                Log.d("getBookError","${error.message} ")

                toastMessage.value = error.message
            })
    }
}