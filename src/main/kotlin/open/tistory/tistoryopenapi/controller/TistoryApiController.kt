package open.tistory.tistoryopenapi.controller

import open.tistory.tistoryopenapi.adapter.external.tistory.TistoryExternalApiAdaptor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpSession

@RestController
class TistoryApiController(
        private val tistoryExternalApiAdaptor: TistoryExternalApiAdaptor
) {

    @GetMapping("/auth")
    fun presentLoginURL(httpServletResponse: HttpServletResponse) {
        return httpServletResponse.sendRedirect(tistoryExternalApiAdaptor.getLoginURL());
    }

    @GetMapping("/login")
    fun login(httpSession: HttpSession, @RequestParam("code") code: String) : String {
        val accessToken = tistoryExternalApiAdaptor.loginWithCode(code);
        httpSession.setAttribute("accessToken", accessToken)
        return code;
    }

}