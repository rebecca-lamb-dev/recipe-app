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
class GetRandomMealUseCaseImplTest {

    @Mock
    private lateinit var mealRepository: MealRepository

    @Test
    fun getRandomMeal() = runBlocking<Unit> {
        val expectedMeal = Meal("12345", "popcorn", listOf(), "thumb", "category", "area", "1\r\n2\r\n3")

        `when`(mealRepository.getRandomMeal()).thenReturn(Success(expectedMeal))

        val getRandomMealUseCase = GetRandomMealUseCaseImpl(mealRepository)

        assertThat(getRandomMealUseCase()).isEqualTo(Success(expectedMeal))
    }

}