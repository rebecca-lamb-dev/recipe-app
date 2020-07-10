package lamb.rebecca.data.network

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class ApiTest {

    companion object {
        private val URL = "http://my-test-url/"
    }

    @Test
    fun createRetrofitWithBaseUrl() {
        val retrofit = Api.retrofit(URL)
        assertThat(retrofit.baseUrl().toString()).isEqualTo(URL)
    }

}