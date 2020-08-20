package lamb.rebecca.recipeapp.presentation.main.ui.meallist

import androidx.test.espresso.Espresso
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import io.mockk.coEvery
import io.mockk.mockk
import lamb.rebecca.domain.model.Meal
import lamb.rebecca.domain.model.MeasuredIngredient
import lamb.rebecca.domain.model.Success
import lamb.rebecca.domain.usecase.GetMealsByLetterUseCase
import lamb.rebecca.recipeapp.R
import lamb.rebecca.recipeapp.RecyclerViewMatcher
import lamb.rebecca.recipeapp.hasDescendentWithIdAndText
import lamb.rebecca.recipeapp.launchFragmentInHiltContainer
import lamb.rebecca.recipeapp.presentation.main.ui.ImageLoaderModule
import lamb.rebecca.recipeapp.presentation.main.ui.MealByLetterModule
import lamb.rebecca.recipeapp.presentation.main.ui.helpers.ImageLoader
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@UninstallModules(MealByLetterModule::class, ImageLoaderModule::class)
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class MealsFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @BindValue
    @JvmField
    val getMealsByLetterUseCase: GetMealsByLetterUseCase = mockk()

    @BindValue
    @JvmField
    val imageLoader: ImageLoader = mockk(relaxed = true)

    @Test
    fun canDisplayMealList() {
        val expectedMeal =
            Meal(
                "12345",
                "cake",
                listOf(MeasuredIngredient("ingredient", "measurement")),
                "thumb",
                "category",
                "area",
                "1\r\n2\r\n3"
            )

        coEvery { getMealsByLetterUseCase("b") } returns Success(listOf(expectedMeal))

        launchFragmentInHiltContainer(MealsFragment::class.java)

        val firstItem = Espresso.onView(RecyclerViewMatcher(R.id.meal_list).atPosition(0))
        firstItem.hasDescendentWithIdAndText(R.id.label, "cake")
    }
}