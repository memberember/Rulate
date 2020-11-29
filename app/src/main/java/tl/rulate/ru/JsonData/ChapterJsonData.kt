package tl.rulate.ru.JsonData

data class ChapterJsonData(val status: String, val msg: String, val response: Response) {

    data class Response(
        var id: Int,
        var title: String,
        var text: String,
        var comments: List<String>
//        var prev_chap: Int,
//        var next_chap: Int
    )
}
