package lamb.rebecca.data.network

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import lamb.rebecca.data.MealFaker
import lamb.rebecca.data.ResourceUtils
import lamb.rebecca.data.network.MealDbService.Companion.mealDbService
import lamb.rebecca.data.network.model.MealListResponse
import lamb.rebecca.data.network.model.RandomMealListResponse
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException

@ExperimentalCoroutinesApi
class MealDbServiceTest {

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun mealDbService_networkError() {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(400)
        )

        val retrofit = Api.retrofit(mockWebServer.url("/").toString())
        val service = mealDbService(retrofit)

        assertThatThrownBy { runBlocking<Unit> { service.getRandomMeal() } }
            .isInstanceOf(HttpException::class.java)
    }

    @Test
    fun mealDbService_canReturnRandomMeal() = runBlocking<Unit> {
        val exampleResponse = ResourceUtils.readResourceAsString("/random_meal_response.json")
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(exampleResponse)
        )

        val retrofit = Api.retrofit(mockWebServer.url("/").toString())
        val service = mealDbService(retrofit)

        val mealResponse = service.getRandomMeal()
        assertThat(mealResponse).isEqualTo(expectedRandomMealResponse())
    }

    @Test
    fun mealDbService_canReturnMealsForLetter() = runBlocking<Unit> {
        val exampleResponse = ResourceUtils.readResourceAsString("/random_meal_response.json")
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(exampleResponse)
        )

        val retrofit = Api.retrofit(mockWebServer.url("/").toString())
        val service = mealDbService(retrofit)

        val mealResponse = service.getMealsForLetter("a")
        assertThat(mealResponse).isEqualTo(expectedMealResponse())
    }

    private fun expectedMealResponse() =
        MealListResponse(
            listOf(
                MealFaker().generateMealEntity()
            )
        )

    private fun expectedRandomMealResponse() =
        RandomMealListResponse(
            listOf(
                MealFaker().generateMealEntity()
            )
        )

}