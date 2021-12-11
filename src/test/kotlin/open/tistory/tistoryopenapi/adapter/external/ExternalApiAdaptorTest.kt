package open.tistory.tistoryopenapi.adapter.external

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class ExternalApiAdaptorTest(

) {

    @Autowired
    lateinit var externalApiAdaptor: ExternalApiAdaptor<String>

    val externalURL = "https://www.naver.com"

    @Test
    @DisplayName("외부 API 요청에 대한 GET 처리가 가능한지 확인합니다.")
    fun testGetMethod() {
        val result = externalApiAdaptor.get(externalURL, clazz = String::class.java)

        assertNotNull(result);
        assertNotNull(result.body);
    }

}