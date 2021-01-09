package tl.rulate.ru.ui.indepentUi


import android.util.Log
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import tl.rulate.ru.Constants
import tl.rulate.ru.JsonData.BookJsonData
import tl.rulate.ru.viewModels.BaseViewModel

object BookViewModel : BaseViewModel() {
    var book: MutableLiveData<BookJsonData.Response> = MutableLiveData()

    fun book(bookId: Int) {
        ApiDpc.book(
            key = Constants.KEY,
            book_id = bookId,
            token = ""

        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->

                // тостер

                // если ответ не нулевой то выполняется действия
                if (result.status == "success") {
                    book.value = result.response

                } else {
                    // todo ответ о том что результат неудачный
                }
            }, { error ->
                Log.d("getBookError", "${error.message} ")
                // todo ответ об ошибке
            })
    }
}