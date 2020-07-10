package lamb.rebecca.recipeapp.presentation.main.ui.mealdetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import lamb.rebecca.domain.model.Meal
import lamb.rebecca.domain.model.Success
import lamb.rebecca.domain.usecase.GetRandomMealUseCase
import lamb.rebecca.recipeapp.CoroutinesTestRule
import lamb.rebecca.recipeapp.getOrAwaitValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test

class MealDetailViewModelTest {

    @get:Rule
    var instantTaskRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var couroutineRule = CoroutinesTestRule()

    val getRandomMealUseCase = mockk<GetRandomMealUseCase>()

    @ExperimentalCoroutinesApi
    @Test
    fun canGetRandomMeal() {
        val expectedMeal = Meal("12345", "cake")

        coEvery { getRandomMealUseCase() } returns Success(expectedMeal)
        val mealDetailViewModel = MealDetailViewModel(getRandomMealUseCase)

        val result = mealDetailViewModel.meal.getOrAwaitValue()
        assertThat(result).isEqualTo(expectedMeal)
    }

}