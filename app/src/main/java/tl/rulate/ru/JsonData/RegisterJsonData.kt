package tl.rulate.ru.JsonData

data class RegisterJsonData(val status: String, val msg: String, val response: Response) {

    // данные ответа
    data class Response(
        val id: Int,
        val balance: Int,
        val not_adult: Int,
        val token: String,
        val login: String,
        val avatar: String
    )
}