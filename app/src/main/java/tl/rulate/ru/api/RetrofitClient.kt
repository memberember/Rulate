package tl.rulate.ru.api

import android.util.Log
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {


    // URL программного интерфейса (API)
    private val BASE_URL = "https://tl.rulate.ru/api/"

    // ленивая инициализация
    val instance: Api by lazy {

        //Converter
        val gson = GsonBuilder().setLenient().create()

        // переопределение вывода логов
        val interceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.d("OkHttp", message)
            }
        })

        // уровень логов
        interceptor.level = (HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
        retrofit.create(Api::class.java)
    }
}


