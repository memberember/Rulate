package tl.rulate.ru.ui.indepentUi


import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import tl.rulate.ru.Constants
import tl.rulate.ru.JsonData.BookJsonData
import tl.rulate.ru.viewModels.BaseViewModel

object BookViewModel : BaseViewModel() {
    var book: MutableLiveData<BookJsonData.Response> = MutableLiveData()
    var toastMessage: MutableLiveData<String> = MutableLiveData()
    var chapterText: MutableLiveData<String> = MutableLiveData()


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

    fun addToBookmark(bookId: Int,token:String) {
        ApiDpc.addBookmark(
            key = Constants.KEY,
            book_id = bookId,
            token = token

        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->

                // тостер

                // если ответ не нулевой то выполняется действия
                if (result.status == "success") {
                    toastMessage.value = result.response

                } else {
                    // todo ответ о том что результат неудачный
                }
            }, { error ->
                Log.d("getBookError", "${error.message} ")
                // todo ответ об ошибке
            })
    }

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
                    chapterText.value = result.response.text

                } else {
                    chapterText.value = result.msg
                }
            }, { error ->
                Log.d("getBookError", "${error.message} ")

                toastMessage.value = error.message
            })
    }
}