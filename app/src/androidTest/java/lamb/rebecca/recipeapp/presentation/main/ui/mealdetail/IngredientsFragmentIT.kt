package lamb.rebecca.recipeapp.presentation.main.ui.mealdetail

import androidx.test.espresso.Espresso.onView
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
import lamb.rebecca.domain.usecase.GetRandomMealUseCase
import lamb.rebecca.recipeapp.R
import lamb.rebecca.recipeapp.RecyclerViewMatcher
import lamb.rebecca.recipeapp.hasDescendentWithIdAndText
import lamb.rebecca.recipeapp.launchFragmentInHiltContainer
import lamb.rebecca.recipeapp.presentation.main.ui.PresentationModule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@UninstallModules(PresentationModule::class)
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class IngredientsFragmentIT {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @BindValue
    @JvmField
    val getRandomMealUseCase: GetRandomMealUseCase = mockk()

    @Test
    fun canDisplayIngredientsInRecyclerView() {
        coEvery { getRandomMealUseCase() } returns Success(
            Meal(
                "test", "test", listOf(
                    MeasuredIngredient("ingredient 1", "measurement 1"),
                    MeasuredIngredient("ingredient 2", "measurement 2")
                ),
                "test-thumbnail"
            )
        )

        val scenario = launchFragmentInHiltContainer<IngredientsFragment>()


        val firstItem = onView(RecyclerViewMatcher(R.id.ingredients).atPosition(0))
        firstItem.hasDescendentWithIdAndText(R.id.ingredient, "ingredient 1")
        firstItem.hasDescendentWithIdAndText(R.id.measurement, "measurement 1")

        val secondItem = onView(RecyclerViewMatcher(R.id.ingredients).atPosition(1))
        secondItem.hasDescendentWithIdAndText(R.id.ingredient, "ingredient 2")
        secondItem.hasDescendentWithIdAndText(R.id.measurement, "measurement 2")
    }


}