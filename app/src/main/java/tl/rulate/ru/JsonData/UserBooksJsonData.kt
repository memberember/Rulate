package tl.rulate.ru.JsonData

class UserBooksJsonData(val status: String, val msg: String, val response: Response) {

    // данные ответа
    data class Response(
        val bookList: List<Book>
    )

    class Book(
        val id: Int,
        val s_title: String,
        val t_title: String,
        val n_chapters: Int,
        val lang: String,
        val role: Int,
        val is_owner: String,
        val status: String,
        val rating: String,
        val likes: Int,
        val ready: String,
        val ac_read: String,
        val ac_gen: String,
        val ac_tr: String,
        val adult: Int
    )
}
