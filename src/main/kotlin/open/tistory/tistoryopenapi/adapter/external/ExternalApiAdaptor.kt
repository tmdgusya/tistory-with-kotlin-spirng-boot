package open.tistory.tistoryopenapi.adapter.external

import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.exchange
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

@Component
class ExternalApiAdaptor<T>(
    private val restTemplate: RestTemplate
) {

    public fun get(
        url: String,
        httpHeaders: HttpHeaders? = null,
        body: JvmType.Object? = null,
        clazz: Class<T> = JvmType.Object::class as Class<T>
    ): ResponseEntity<T> {
        return callExternalApi(url, HttpMethod.GET, httpHeaders, body, clazz)
    }

    public fun post(
        url: String,
        httpHeaders: HttpHeaders? = null,
        body: JvmType.Object? = null,
        clazz: Class<T> = JvmType.Object::class as Class<T>
    ): ResponseEntity<T> {
        return callExternalApi(url, HttpMethod.POST, httpHeaders, body, clazz)
    }

    private fun callExternalApi(
        url: String,
        httpMethod: HttpMethod,
        httpHeaders: HttpHeaders? = null,
        body: JvmType.Object? = null,
        clazz: Class<T>
    ): ResponseEntity<T> {
        return restTemplate.exchange(url, httpMethod, HttpEntity(body, httpHeaders), clazz)
    }
}
