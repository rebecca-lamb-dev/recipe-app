package lamb.rebecca.data.repository

import kotlinx.coroutines.runBlocking
import lamb.rebecca.data.MealFaker
import lamb.rebecca.data.ResourceUtils
import lamb.rebecca.data.network.Api
import lamb.rebecca.data.network.MealDbService
import lamb.rebecca.domain.model.*
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

class MealRepositoryImplIT {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var service: MealDbService

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        val retrofit = Api.retrofit(mockWebServer.url("/").toString())
        service = MealDbService.mealDbService(retrofit)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun canGetRandomMeal() = runBlocking<Unit> {
        val exampleResponse = ResourceUtils.readResourceAsString("/random_meal_response.json")
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(exampleResponse)
        )


        val mealRepo = MealRepositoryImpl(service)

        val result = mealRepo.getRandomMeal()

        assertThat(result).isEqualTo(Success(MealFaker().generateMeal()))
    }

    @Test
    fun canReturnDataErrorForBadJson() = runBlocking<Unit> {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody("\"not_a_meal\":\"bad_data\"")
        )

        val mealRepo = MealRepositoryImpl(service)

        val result = mealRepo.getRandomMeal()

        assertThat(result).isEqualTo(Failure(InvalidDataError))
    }

    @Test
    fun canReturnHttpError() = runBlocking<Unit> {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(400)
        )

        val mealRepo = MealRepositoryImpl(service)

        val result = mealRepo.getRandomMeal()

        assertThat(result).isEqualTo(Failure(HttpError(400, "Client Error")))
    }

}