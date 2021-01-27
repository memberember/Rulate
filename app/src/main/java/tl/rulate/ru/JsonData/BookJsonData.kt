package tl.rulate.ru.JsonData

data class BookJsonData(val status: String, val msg: String, val response: Response) {

    data class Response(
        val id: Int,
        //val cat: String,
        val s_title: String,
        val t_title: String,
        val description: String,
        val n_chapers: Int,
        val lang: String,
        val last_activity: Int,
        val status: String,
        val rating: String,
        val quality_rating: String,
        val likes: Int,
        val author: String,
        val img: String,
//        val additional_imgs
        val writer: String,
        val publisher: String,
        val year: String,
        val chapters_total: Int,
        val adult: Int,
        val team:String,
        val chapters: List<Chapter>,
        val comments: List<Comment>,
        val bookmark: Int,
        val origin_status: String,
        val genres: List<Genre>,
        val last_readed: Int,
        val pages_cnt: Int
    )

    data class Genre(
        val id: Int,
        val title: String
    )

    data class Chapter(
        val id: Int,
        val title: String,
        val status: String,
        val can_read: Boolean,
        val new: Boolean,
        val ord: Int,
        val has_audio: Boolean
//    "audio":
    )

    data class Comment(
        val body: String,
        val time: Int,
        val author: String,
        val avatar: String
    )
}