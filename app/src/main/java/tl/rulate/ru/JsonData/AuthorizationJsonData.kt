package tl.rulate.ru.JsonData

// дата класс авторизации
data class AuthorizationJsonData(val status: String, val msg: String, val response: Response){

    // данные ответа
    data class Response(
        val id: Int,
        val token: String,
        val login: String,
        val avatar: String,
        val avatar_big: String,
        val balance: Int,
        val not_adult: Int
    )
}
