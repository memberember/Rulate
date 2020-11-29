package tl.rulate.ru.viewModels

import androidx.lifecycle.ViewModel
import tl.rulate.ru.SharedPrefManager
import tl.rulate.ru.api.RetrofitClient
import kotlin.coroutines.coroutineContext

abstract class BaseViewModel :ViewModel(){
    val ApiDpc = RetrofitClient.instance
}