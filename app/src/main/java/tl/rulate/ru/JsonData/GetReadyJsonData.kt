package tl.rulate.ru.JsonData

data class GetReadyJsonData(val status: String, val msg: String, val response: List<Title>) {

    data class Response(
        val titles: List<Title>
    )

    data class Title(
        val id: Int,
        val book_id: Int,
        val s_title: String,
        val t_title: String,
        val title: String,
        val img: String,
        val ready_date: String,
        val lang: String,
        val adult: Int,
        val rating: Double
    )
}
