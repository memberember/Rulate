data class SearchBooksJsonData(val status: String, val msg: String, val response: List<Titles>) {

    data class Titles(
        var id: Int,
        var s_title: String,
        var t_title: String,
        var n_chapters: Int,
        var lang: String,
        var last_activity: Int,
        var status: String,
        var rating: String,
        var author: String,
        var ready: String,
        var ac_read: String,
        var ac_gen: String,
        var ac_tr: String,
        var adult: Int,
        var img: String
    )

}