package open.tistory.tistoryopenapi.config

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.web.client.RestTemplate
import java.time.Duration

@Configuration
class RestTemplateConfig {

    @Bean
    fun restTemplate(): RestTemplate {
        return RestTemplateBuilder()
            .setReadTimeout(Duration.ofMillis(5000))
            .setConnectTimeout(Duration.ofMillis(3000))
            .build()
    }

}