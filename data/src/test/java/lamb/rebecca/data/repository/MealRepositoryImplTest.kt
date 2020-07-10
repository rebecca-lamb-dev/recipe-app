package lamb.rebecca.data.repository

import com.squareup.moshi.JsonDataException
import kotlinx.coroutines.runBlocking
import lamb.rebecca.data.network.MealDbService
import lamb.rebecca.data.network.model.MealDataModel
import lamb.rebecca.data.network.model.RandomMealResponse
import lamb.rebecca.domain.model.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.HttpException

@RunWith(MockitoJUnitRunner::class)
class MealRepositoryImplTest {

    @Mock
    private lateinit var mealDbService: MealDbService

    @Mock
    private lateinit var httpException: HttpException

    @Test
    fun canGetRandomMeal() = runBlocking<Unit> {
        val mealRepo = MealRepositoryImpl(mealDbService)

        `when`(mealDbService.getRandomMeal()).thenReturn(
            RandomMealResponse(
                listOf(
                    MealDataModel(
                        "123",
                        "bread"
                    )
                )
            )
        )

        val result = mealRepo.getRandomMeal()

        assertThat(result).isEqualTo(Success(Meal("123", "bread")))
    }

    @Test
    fun canReturnDataErrorForBadJson() = runBlocking<Unit> {
        val mealRepo = MealRepositoryImpl(mealDbService)

        `when`(mealDbService.getRandomMeal()).thenThrow(JsonDataException())

        val result = mealRepo.getRandomMeal()

        assertThat(result).isEqualTo(Failure(InvalidDataError))
    }

    @Test
    fun canReturnDataErrorForMissingData() = runBlocking<Unit> {
        val mealRepo = MealRepositoryImpl(mealDbService)

        `when`(mealDbService.getRandomMeal()).thenReturn(RandomMealResponse(listOf()))

        val result = mealRepo.getRandomMeal()

        assertThat(result).isEqualTo(Failure(InvalidDataError))
    }

    @Test
    fun canReturnHttpError() = runBlocking<Unit> {
        val mealRepo = MealRepositoryImpl(mealDbService)

        `when`(httpException.code()).thenReturn(400)
        `when`(httpException.message()).thenReturn("http error")
        `when`(mealDbService.getRandomMeal()).thenThrow(httpException)

        val result = mealRepo.getRandomMeal()

        assertThat(result).isEqualTo(Failure(HttpError(400, "http error")))
    }


}