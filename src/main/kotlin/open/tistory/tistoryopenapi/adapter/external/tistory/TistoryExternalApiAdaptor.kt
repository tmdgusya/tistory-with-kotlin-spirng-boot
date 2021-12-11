package open.tistory.tistoryopenapi.adapter.external.tistory

import open.tistory.tistoryopenapi.adapter.external.ExternalApiAdaptor
import open.tistory.tistoryopenapi.env.TistoryEnvProvider
import org.springframework.stereotype.Component

@Component
class TistoryExternalApiAdaptor(
        private val externalApiAdaptor: ExternalApiAdaptor<String>,
        private val tistoryEnvProvider: TistoryEnvProvider
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

        return accessToken.body.toString();
    }

    private fun createAccessTokenExternalAPIUrl(code: String) : String {
        return GET_ACCESS_TOKEN_API_URL.plus("&code=${code}")
    }

    private fun createBlogPostList(accessToken: String) : String {
        return "https://www.tistory.com/apis/blog/info?" +
                "  access_token=${accessToken}" +
                "  &output=json"
    }

}