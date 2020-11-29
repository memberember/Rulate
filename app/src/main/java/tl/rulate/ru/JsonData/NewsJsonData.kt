package tl.rulate.ru.JsonData

class NewsJsonData(val status: String, val msg: String, val response: Response) {

    // данные ответа
    data class Response(
        val articleList: List<Article>
    )

    class Article(
        val id: Int,
        val title: String,
        val body: String,
        val time: Int,
        val author: String,
        val avatar: String
    )
}