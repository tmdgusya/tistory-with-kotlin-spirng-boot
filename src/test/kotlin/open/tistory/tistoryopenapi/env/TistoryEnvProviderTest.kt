package open.tistory.tistoryopenapi.env

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class TistoryEnvProviderTest {

    @Autowired
    private lateinit var tistoryEnvProvider: TistoryEnvProvider

    @Test
    @DisplayName("TistoryEnvProvider 를 통해서 properties 파일의 Environment 를 잘 가져오는지 확인합니다.")
    fun verify() {
        // given
        val expectedClientId = "testClient"
        val expectedRedirectUrl = "http://localhost:8080/test_callback"
        val expectedResponseType = "token"

        // when
        var clientId = tistoryEnvProvider.clientId
        val redirectUri = tistoryEnvProvider.redirectUri
        val responseType = tistoryEnvProvider.responseType

        // then
        assertThat(clientId).isEqualTo(expectedClientId)
        assertThat(redirectUri).isEqualTo(expectedRedirectUrl)
        assertThat(expectedResponseType).isEqualTo(responseType)
    }
}
