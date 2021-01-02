package tl.rulate.ru.JsonData

data class BlogJsonData(val status: String, val msg: String, val response: List<PostData>) {

    // данные ответа
    data class PostData(
        val id: Int,
        val title: String,
        val body: String,
        val time: Int,
        val author: String,
        val avatar: String,
        val comments_cnt: Int
    )
}