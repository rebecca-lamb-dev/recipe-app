package lamb.rebecca.domain.usecase

import kotlinx.coroutines.runBlocking
import lamb.rebecca.domain.model.Meal
import lamb.rebecca.domain.model.Success
import lamb.rebecca.domain.repository.MealRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetMealsByLetterUseCaseImplTest {

    @Mock
    private lateinit var mealRepository: MealRepository

    @Test
    fun canGetMealsByLetter() = runBlocking<Unit> {
        val expectedMealList = listOf(
            Meal(
                "12345",
                "popcorn",
                listOf(),
                "thumb",
                "category",
                "area",
                "1\r\n2\r\n3"
            )
        )
        `when`(mealRepository.getMealsByLetter("a")).thenReturn(
            Success(
                expectedMealList
            )
        )

        val getMealsByLetterUseCase = GetMealsByLetterUseCaseImpl(mealRepository)

        assertThat(getMealsByLetterUseCase("a")).isEqualTo(Success(expectedMealList))
    }

}