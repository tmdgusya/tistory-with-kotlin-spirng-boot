package open.tistory.tistoryopenapi.env

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource

@Configuration
@PropertySource("classpath:tistory.properties")
class TistoryEnvProvider {

    @Value("\${clientId}")
    lateinit var clientId: String

    @Value("\${redirectUri}")
    lateinit var redirectUri: String

    @Value("\${responseType}")
    lateinit var responseType: String
}
