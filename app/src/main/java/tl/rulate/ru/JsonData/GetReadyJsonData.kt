package tl.rulate.ru.JsonData

import Title

data class GetReadyJsonData(val status: String, val msg: String, val response: List<NovelChapter>) {

    data class NovelChapter(
        val id: Int,
        val t_title: String,
        val s_title: String,
        val img: String,
        val adult: Int,
        val book_id: Int,
        val title: String,
        val ready_date: String,
        val lang: String,
        val rating: Double        )
}
