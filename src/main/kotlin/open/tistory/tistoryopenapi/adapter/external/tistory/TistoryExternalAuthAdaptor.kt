package open.tistory.tistoryopenapi.adapter.external.tistory

import com.fasterxml.jackson.databind.ObjectMapper
import open.tistory.tistoryopenapi.adapter.external.ExternalApiAdaptor
import open.tistory.tistoryopenapi.env.TistoryEnvProvider
import org.springframework.stereotype.Component

@Component
class TistoryExternalAuthAdaptor(
        private val externalApiAdaptor: ExternalApiAdaptor<String>,
        private val tistoryEnvProvider: TistoryEnvProvider,
        private val objectMapper: ObjectMapper
) : TistoryExternalApi {

    private val GET_TOKEN_API_URL = "https://www.tistory.com/oauth/authorize?" +
            "client_id=${tistoryEnvProvider.clientId}" +
            "&response_type=${tistoryEnvProvider.responseType}" +
            "&redirect_uri=${tistoryEnvProvider.blogUrl}"

    private val GET_ACCESS_TOKEN_API_URL = "https://www.tistory.com/oauth/access_token?" +
            "grant_type=authorization_code" +
            "&client_id=${tistoryEnvProvider.clientId}" +
            "&client_secret=${tistoryEnvProvider.clientSecret}" +
            "&redirect_uri=${tistoryEnvProvider.redirectUri}"

    override fun getLoginURL(): String {
        return GET_TOKEN_API_URL;
    }

    override fun loginWithCode(code: String) : String {
        val externalApiURL = createAccessTokenExternalAPIUrl(code);
        val accessToken = externalApiAdaptor.get(externalApiURL, clazz = String::class.java)

        if (accessToken.body === null) {
            return "none"
        }

        val readTree = objectMapper.readTree(accessToken.body.toString())

        return readTree.get("access_token").toString();
    }

    override fun getBlogInfo(accessToken: String): String {
        val blogInfoExternalApiURL = createBlogPostList(accessToken);
        val blogJson = externalApiAdaptor.get(blogInfoExternalApiURL, clazz = String::class.java)

        /**
         * 후에 에러처리 필요함
         */
        if (blogJson.body === null) {
            return "none"
        }

        val readTree = objectMapper.readTree(blogJson.body.toString())
        println(readTree)

        return readTree.toString();
    }

    private fun createAccessTokenExternalAPIUrl(code: String) : String {
        return GET_ACCESS_TOKEN_API_URL.plus("&code=${code}")
    }

    private fun createBlogPostList(accessToken: String) : String {
        return "https://www.tistory.com/apis/blog/info?" +
                "access_token=${accessToken}" +
                "&output=json"
    }

}