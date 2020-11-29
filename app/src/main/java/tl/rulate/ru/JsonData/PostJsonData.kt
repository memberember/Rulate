package tl.rulate.ru.JsonData

class PostJsonData(val status: String, val msg: String, val response: Response) {

    // данные ответа
    data class Response(
        val id: Int,
        val title: String,
        val body: String,
        val time: Int,
        val author: String,
        val avatar: String,
        val n_comments: Int
    )
}
