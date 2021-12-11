package open.tistory.tistoryopenapi.adapter.external.tistory

import javax.servlet.http.HttpSession

interface TistoryExternalApi {

    /**
     * Code 값을 추출한다.
     */
    fun getLoginURL() : String

    /**
     * Code 값을 받아서 Access Token 을 추출한다.
     */
    fun loginWithCode(code: String) : String

    /**
     * Blog 정보를 가져오는 외부 API 를 호출합니다.
     */
    fun getBlogInfo(accessToken: String) : String
}