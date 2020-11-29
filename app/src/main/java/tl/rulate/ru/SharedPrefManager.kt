package tl.rulate.ru

import android.content.Context
import android.util.Log
import tl.rulate.ru.data.DataUser

class SharedPrefManager private constructor(private val mCtx: Context) {

    val isLoggedIn: Boolean
        get() {
            val sharedPreferences =
                mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return sharedPreferences.getInt("id", -1) != -1
        }

    val user: DataUser
        get() {
            val sharedPreferences =
                mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            var dataUser = DataUser()
            dataUser.id = sharedPreferences.getInt("id", -1)
            dataUser.login = sharedPreferences.getString("login", null) ?: ""
            dataUser.avatar = sharedPreferences.getString("avatar", null) ?: ""
            dataUser.avatar_big = sharedPreferences.getString("avatar_big", null) ?: ""
            dataUser.status = sharedPreferences.getString("status", null) ?: ""
            dataUser.sex = sharedPreferences.getString("sex", null) ?: ""
            dataUser.books_count = sharedPreferences.getInt("books_count", 0)
            dataUser.translated = sharedPreferences.getString("translated", null) ?: ""
            dataUser.karma = sharedPreferences.getInt("karma", 0)
            dataUser.cdate = sharedPreferences.getString("cdate", null) ?: ""
            dataUser.name = sharedPreferences.getString("name", null) ?: ""
            dataUser.country = sharedPreferences.getString("country", null) ?: ""
            dataUser.city = sharedPreferences.getString("city", null) ?: ""
            dataUser.birthday = sharedPreferences.getString("birthday", null) ?: ""
            dataUser.confirmed = sharedPreferences.getBoolean("confirmed", false)
            dataUser.comments_count = sharedPreferences.getInt("comments_count", 0)
            return dataUser
        }


    fun saveUser(user: DataUser) {

        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putInt("id", user.id)
        editor.putString("login", user.login)
        editor.putString("avatar", user.avatar)
        editor.putString("avatar_big", user.avatar_big)
        editor.putString("status", user.status)
        editor.putString("sex", user.sex)
        editor.putInt("books_count", user.books_count)
        editor.putString("translated", user.translated)
        editor.putInt("karma", user.karma)
        editor.putString("cdate", user.cdate)
        editor.putString("name", user.name)
        editor.putString("country", user.country)
        editor.putString("city", user.city)
        editor.putString("birthday", user.birthday)
        editor.putBoolean("confirmed", user.confirmed)
        editor.putInt("comments_count", user.comments_count)
        editor.apply()

    }

    fun clear() {
        Log.d("Debug", "clear")

        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    companion object {
        private val SHARED_PREF_NAME = "shp"
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