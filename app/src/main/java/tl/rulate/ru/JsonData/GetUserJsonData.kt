package tl.rulate.ru.JsonData

// дата класс данных пользователя
data class GetUserJsonData(val status: String, val msg: String, val response: Response) {

    // данные ответа
    data class Response(
        val id: Int,
        val login: String,
        val avatar: String,
        val avatar_big: String,
        val status: String,
        val sex: String,
        val books_count: Int,
        val translated: String,
        val karma: Int,
        val cdate: String,
        val name: String,
        val country: String,
        val city: String,
        val birthday: String,
        val confirmed: Boolean,
        val comments_count: Int
    )
}