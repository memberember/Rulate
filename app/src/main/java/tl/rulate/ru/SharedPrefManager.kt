package tl.rulate.ru

import android.content.Context
import tl.rulate.ru.data.SharedPrefBookData
import tl.rulate.ru.data.SharedPrefData

class SharedPrefManager private constructor(private val mCtx: Context) {

    val isLoggedIn: Boolean
        get() {
            val sharedPreferences =
                mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return sharedPreferences.getInt("id", -1) != -1
        }

    val user: SharedPrefData
        get() {
            val sharedPreferences =
                mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            var dataUser = SharedPrefData()
            dataUser.id = sharedPreferences.getInt("id", -1)
            dataUser.token = sharedPreferences.getString("token", null) ?: ""
            return dataUser
        }


    fun saveUser(user: SharedPrefData) {

        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putInt("id", user.id)
        editor.putString("token", user.token)
        editor.apply()
    }

    fun clear() {
        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    val book: SharedPrefBookData
        get() {
            val sharedPreferences =
                mCtx.getSharedPreferences(SHARED_PREF_BOOK_DATA_NAME, Context.MODE_PRIVATE)
            var dataBook = SharedPrefBookData()
            dataBook.lastBookId = sharedPreferences.getInt("lastBookId", -1)
            dataBook.lastChapterId = sharedPreferences.getInt("lastChapterId", -1)
            return dataBook
        }


    fun saveBook(book: SharedPrefBookData) {

        val sharedPreferences =
            mCtx.getSharedPreferences(SHARED_PREF_BOOK_DATA_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putInt("lastBookId", book.lastBookId)
        editor.putInt("lastChapterId", book.lastChapterId)
        editor.apply()
    }

    companion object {
        private const val SHARED_PREF_NAME = "shp"
        private const val SHARED_PREF_BOOK_DATA_NAME = "shpbd"

        private var mInstance: SharedPrefManager? = null

        @Synchronized
        fun getInstance(mCtx: Context): SharedPrefManager {
            if (mInstance == null) {
                mInstance = SharedPrefManager(mCtx)
            }
            return mInstance as SharedPrefManager
        }
    }

}