package lamb.rebecca.data.network

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import lamb.rebecca.data.ResourceUtils
import lamb.rebecca.data.network.MealDbService.Companion.mealDbService
import lamb.rebecca.data.network.model.MealEntity
import lamb.rebecca.data.network.model.MeasuredIngredientEntity
import lamb.rebecca.data.network.model.RandomMealResponse
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
        assertThat(mealResponse).isEqualTo(expectedMealResponse())
    }

    private fun expectedMealResponse() =
        RandomMealResponse(
            listOf(
                MealEntity(
                    "52767", "Bakewell tart",
                    listOf(
                        MeasuredIngredientEntity(
                            "plain flour",
                            "175g/6oz"
                        ),
                        MeasuredIngredientEntity(
                            "chilled butter",
                            "75g/2½oz"
                        ),
                        MeasuredIngredientEntity(
                            "cold water",
                            "2-3 tbsp"
                        ),
                        MeasuredIngredientEntity(
                            "raspberry jam",
                            "1 tbsp"
                        ),
                        MeasuredIngredientEntity(
                            "butter",
                            "125g/4½oz"
                        ),
                        MeasuredIngredientEntity(
                            "caster sugar",
                            "125g/4½oz"
                        ),
                        MeasuredIngredientEntity(
                            "ground almonds",
                            "125g/4½oz"
                        ),
                        MeasuredIngredientEntity(
                            "free-range egg, beaten",
                            "1"
                        ),
                        MeasuredIngredientEntity(
                            "almond extract",
                            "½ tsp"
                        ),
                        MeasuredIngredientEntity(
                            "flaked almonds",
                            "50g/1¾oz"
                        )
                    )
                )
            )
        )

}