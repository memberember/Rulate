package tl.rulate.ru.api

import AddBookmarkJsonData
import AdsJsonData
import NewBooksJsonData
import SearchBooksJsonData
import io.reactivex.Single
import retrofit2.http.POST
import retrofit2.http.Query
import tl.rulate.ru.JsonData.*

interface Api {

    // ##############################################ПРОФИЛЬ##############################################
    // запрос на создание пользователя
    @POST("register") // название метода
    fun register(
        @Query("key") key: String, // key
        @Query("login") login: String, // login
        @Query("pass") pass: String, // pass
        @Query("email") email: String, // email
        @Query("sex") sex: String, // пол (m - мужчина, f - женщина, x - не указан)
        @Query("lang") lang: Int // язык
    ): Single<RegisterJsonData> // отправка каллбека и преобразование позже в дата класс RegisterJsonData

    // запрос на авторизацию пользователя
    @POST("auth") // название метода
    fun authorizeUzer(
        @Query("key") key: String,
        @Query("login") login: String,
        @Query("pass") pass: String
    ): Single<AuthorizationJsonData> // коллбек на преобразование ответа в дата класс AuthorizationJsonData

    // запрос на изменение пароля
    @POST("changePass") // название метода
    fun changePass(
        @Query("key") key: String,
        @Query("token") token: String,
        @Query("new_pass") new_pass: String
    ): Single<ChangePasswordJsonData>

    /*

        // Список всех переводов, в которых участвует пользователь
        @POST("userBooks") // название метода
        fun userBooks(
            @Query("user_id") user_id: Int
        ): Single<UserBooksJsonData>

        // Список всех комментариев пользователя
        @POST("userComments") // название метода
        fun userComments(
            @Query("token") token: String,
            @Query("mode") mode: String,
            @Query("limit") limit: Int,
            @Query("page") page: Int
        ): Single<UserCommentsJsonData>

        // Получить карму пользователя
        @POST("karma") // название метода
        fun karma(
            @Query("user_id") user_id: Int,
            @Query("type") type: Int
        ): Single<KarmaJsonData>

        // Изменить карму пользователя
        @POST("addKarma") // название метода
        fun addKarma(
            @Query("token") token: String,
            @Query("to_user") to_user: Int,
            @Query("mark") mark: Int,
            @Query("note") note: String
        ): Single<AddKarmaJsonData>


        // Получить оповещения
        @POST("notices")
        fun notices(
            @Query("token") token: String,
            @Query("limit") limit: Int,
            @Query("page") page: Int,
            @Query("new_only") new_only: Int
        ): Single<noticesJsonData>

        // Принять приглашение на участие в переводе
        @POST("membershipAccept")
        fun membershipAccept(
            @Query("token") token: String,
            @Query("book_id") book_id: Int
        ): Single<membershipAcceptJsonData>

        // Отказаться от приглашения участвовать в переводе
        @POST("membershipDecline")
        fun membershipDecline(
            @Query("token") token: String,
            @Query("book_id") book_id: Int
        ): Single<membershipDeclineJsonData>

        // Получить контакты пользователя
        @POST("userContacts")
        fun userContacts(
            @Query("user_id") user_id: Int
        ): Single<userContactsJsonData>

        // Получить отслеживаемые главы
        @POST("bookmarksChapters")
        fun bookmarksChapters(
            @Query("token") token: String,
            @Query("limit") limit: Int,
            @Query("page") page: Int
        ): Single<bookmarksChaptersJsonData>
    */
    // Получить закладки пользователя
    @POST("bookmarks")
    fun bookmarks(
        @Query("key") key: String,
        @Query("token") token: String
    ): Single<BookmarksJsonData>

    // Добавить перевод в закладки
    @POST("addBookmark")
    fun addBookmark(
        @Query("key") key: String,
        @Query("book_id") book_id: Int,
        @Query("token") token: String
    ): Single<AddBookmarkJsonData>
/*
    // Удалить перевод из закладок
    @POST("removeBookmark")
    fun removeBookmark(
        @Query("book_id") book_id: Int,
        @Query("token") token: String
    ): Single<removeBookmarkJsonData>
*/


    // Получить данные о пользователе
    @POST("getUser") // название метода
    fun getUser(
        @Query("key") key: String, // key
        @Query("user_id") user_id: Int // user_id
    ): Single<GetUserJsonData> // коллбек на преобразование ответа в дата класс GetUserJsonData


    // ##############################################ПОЧТА##############################################
//    // Список всех диалогов пользователя
//    @POST("messages")
//    fun messages(
//        @Query("token") token: String
//    ): Single<messagesJsonData>
//
//
//    // Диалог
//    @POST("chat")
//    fun chat(
//        @Query("token") token: String,
//        @Query("buddy_id") buddy_id: Int
//    ): Single<chatJsonData>
//
//    // Отправить сообщение
//    @POST("write")
//    fun write(
//        @Query("token") token: String,
//        @Query("buddy_id") buddy_id: Int,
//        @Query("text") text: String
//    ): Single<writeJsonData>


    // ##############################################НОВОСТИ#############################################
    // Список всех новостей
    @POST("news")
    fun news(
        @Query("search") search: String,
        @Query("limit") limit: Int,
        @Query("page") page: Int
    ): Single<NewsJsonData>

    // Получить конкретный пост
    @POST("post")
    fun post(
        @Query("post_id") post_id: Int
    ): Single<PostJsonData>


//    ): Single<createPostJsonData>


    //    // Получить все комментарии к посту
//    @POST("postComments")
//    fun postComments(
//        @Query("post_id") post_id: Int
//    ): Single<postCommentsJsonData>
//
//
//    // Получить все комментарии к посту в виде дерева
//    @POST("postCommentsTree")
//    fun postCommentsTree(
//        @Query("post_id") post_id: Int
//    ): Single<postCommentsTreeJsonData>
//
//    // Создать новость
//    @POST("createPost")
//    fun createPost(
//        @Query("token") token: String,
//        @Query("title") title: String,
//        @Query("body") body: String
// ##############################################БЛОГ#############################################
//    // Получить список категорий блога
//    @POST("blogCats")
//    fun blogCats(
//    ): Single<blogCatsJsonData>
//
//
    // Получить все посты из категории блога
    @POST("blog")
    fun blog(
        @Query("key") key: String,
//        @Query("topic") topic: Int,
        @Query("limit") limit: Int,
        @Query("page") page: Int
    ): Single<BlogJsonData>
//
//
//    // ##############################################КОМАНДА#############################################
//    // Данные о команде пользователя
//    @POST("myTeam")
//    fun myTeam(
//        @Query("token") token: String
//    ): Single<myTeamJsonData>
//
//
//    // Участники команды
//    @POST("teamMembers")
//    fun teamMembers(
//        @Query("team_id") team_id: Int
//    ): Single<teamMembersJsonData>
//
//
//    // Переводы команды
//    @POST("teamBooks")
//    fun teamBooks(
//        @Query("team_id") team_id: Int
//    ): Single<teamBooksJsonData>
//
//
//    // ##############################################АНОНСЫ#############################################
//    // Получить список анонсов\новости переводов
//    @POST("announces")
//    fun announces(
//        @Query("limit") limit: Int,
//        @Query("page") page: Int
//    ): Single<announcesJsonData>
//
//
// ##############################################ПЕРЕВОДЫ#############################################
// Получить список последних переведённых глав
    /** Последние обновления **/
    @POST("getReady")
    fun getReady(
        @Query("key") key: String, // key
        @Query("limit") limit: Int = 100,
        @Query("page") page: Int = 1
    ): Single<GetReadyJsonData>

    // Получить список новых книг
    /** Новинки **/
    @POST("newBooks")
    fun newBooks(
        @Query("key") key: String // key
    ): Single<NewBooksJsonData>

    // Получить список новых книг
    /** Реклама **/
    @POST("ads")
    fun ads(
        @Query("key") key: String // key
    ): Single<AdsJsonData>

    // Получить список трендов
    /** Тренды **/
    @POST("trends")
    fun trends(
        @Query("key") key: String // key
    ): Single<AdsJsonData>


//
//    // Получить все комментарии к переводу
//    @POST("bookComments")
//    fun bookComments(
//        @Query("book_id") book_id: Int
//    ): Single<bookCommentsJsonData>
//
//
//    // Получить категории из каталога
//    @POST("catalog")
//    fun catalog(
//    ): Single<catalogJsonData>


//    // Список всех переводов из указанной категории
//    @POST("books")
//    fun books(
//        @Query("cat_id") cat_id: Int
//    ): Single<booksJsonData>


    // Поиск по переводам
    @POST("searchBooks")
    fun searchBooks(
        @Query("key") key: String, // key
        @Query("t") search: String
    ): Single<SearchBooksJsonData>

    // Получить данные о переводе
    @POST("book")
    fun book(
        @Query("key") key: String, // key
        @Query("book_id") book_id: Int,
        @Query("token") token: String
    ): Single<BookJsonData>

    //
    // Получить текст главы
    @POST("chapter")
    fun chapter(
        @Query("key") key: String, // key
        @Query("chapter_id") chapter_id: Int,
        @Query("token") token: String,
        @Query("book_id") book_id: Int = 1
    ): Single<ChapterJsonData>
//
//
//    // ##############################################ОБРАЩЕНИЯ К СИСТЕМЕ#############################################
//    // Получить список доступных языков
//    @POST("getLangs")
//    fun getLangs(): Single<getLangsJsonData>
//
//
//    // Получить список меток
//    @POST("getLabels")
//    fun getLabels(): Single<getLabelsJsonData>
//
//
//    // Получить общий чат сайта
//    @POST("commonChat")
//    fun commonChat(): Single<commonChatJsonData>
//
//
//    // Отправить сообщение в общий чат
//    @POST("commonChatSend")
//    fun commonChatSend(
//        @Query("token") token: String,
//        @Query("message") message: String
//    ): Single<commonChatSendJsonData>
}
