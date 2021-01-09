package tl.rulate.ru.ui.profile

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import tl.rulate.ru.Constants
import tl.rulate.ru.data.DataUser
import tl.rulate.ru.viewModels.BaseViewModel

object ProfileViewModel: BaseViewModel() {
    var dataUser: MutableLiveData<DataUser> = MutableLiveData()
    var toastMessage: MutableLiveData<String> = MutableLiveData()
    var currentFragment: MutableLiveData<Fragment> = MutableLiveData()
//    var userName: MutableLiveData<String> = MutableLiveData("testuser999")
//    var userPass: MutableLiveData<String> = MutableLiveData("testpassword999")
    var userName: MutableLiveData<String> = MutableLiveData("testuser998")
    var userPass: MutableLiveData<String> = MutableLiveData("testpassword998")
    var myUserId: MutableLiveData<Int> = MutableLiveData(-1)
    var myUserToken: MutableLiveData<String> = MutableLiveData()

    fun getUser(userId: Int) {

        ApiDpc.getUser(
            key = Constants.KEY,
            user_id = userId
        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                var resp = result.response

                // если ответ не нулевой то выполняется действия
                if (resp != null) {
                    var userData = DataUser()
                    userData.avatar = resp.avatar ?: ""
                    userData.id = resp.id ?: 0
                    userData.login = resp.login ?: ""
                    userData.avatar_big = resp.avatar_big ?: ""
                    userData.status = resp.status ?: ""
                    userData.sex = resp.sex ?: ""
                    userData.books_count = resp.books_count ?: 0
                    userData.translated = resp.translated ?: ""
                    userData.karma = resp.karma ?: 0
                    userData.cdate = resp.cdate ?: ""
                    userData.name = resp.name ?: ""
                    userData.country = resp.country ?: ""
                    userData.city = resp.city ?: ""
                    userData.birthday = resp.birthday ?: ""
                    userData.confirmed = resp.confirmed ?: false
                    userData.comments_count = resp.comments_count ?: 0
                    dataUser.value = userData
                }

            }, { error ->
                toastMessage.value = error.message
            })
    }

    fun register(
        login: String,
        pass: String,
        email: String,
        sex: String = "x",
        lang: Int
    ) {
        ApiDpc.register(
            key = Constants.KEY,
            login = login,
            pass = pass,
            email = email,
            sex = sex,
            lang = lang
        ).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                toastMessage.value = result.status
            }, { error ->
                toastMessage.value = error.message
            })
    }

    fun authorizeUser(login: String, pass: String) {
        ApiDpc.authorizeUzer(
            key = Constants.KEY,
            login = login,
            pass = pass
        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->

                // тостер
                toastMessage.value = result.msg

                // если ответ не нулевой то выполняется действия
                if (result.response != null) {

                    myUserId.value = result.response.id
                    myUserToken.value = result.response.token
                }

            }, { error ->
                toastMessage.value = error.message
            })
    }


}