package open.tistory.tistoryopenapi.adapter.external.tistory

data class QueryBlogInfo(
        val status: Int,
        val item: Item)

data class Item(
        val id: String,
        val blogs: Array<Blog>
)

data class Blog(val statistics: Statistics)

class Statistics(
        val post: Int,
        val comment: Int,
        val trackback: Int,
        val guestbook: Int
)