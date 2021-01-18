package tl.rulate.ru.JsonData

data class BookmarksJsonData(val status: String, val msg: String, val response: List<Bookmark>) {

    data class Bookmark(
        val s_title: String,
        val t_title: String,
        val n_chapters: Int,
        val lang: String,
        val book_id: Int,
        val type: Int,
        val img: String,
        val last_activity: Int,
        val status: String,
        val rating: String,
        val likes: Int,
        val author: String,
        val ready: String,
        val ac_read: String,
        val ac_gen: String,
        val ac_tr: String,
        val adult: Int,
        val tags: List<String>,
        val genres: List<String>
    )

}