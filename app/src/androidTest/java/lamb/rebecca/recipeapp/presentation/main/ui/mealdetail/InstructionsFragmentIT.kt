package lamb.rebecca.recipeapp.presentation.main.ui.mealdetail

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
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
import lamb.rebecca.recipeapp.launchFragmentInHiltContainer
import lamb.rebecca.recipeapp.presentation.main.ui.PresentationModule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@UninstallModules(PresentationModule::class)
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class InstructionsFragmentIT {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @BindValue
    @JvmField
    val getRandomMealUseCase: GetRandomMealUseCase = mockk()

    @Test
    fun canDisplayInstructions() {
        coEvery {
            getRandomMealUseCase()
        } returns Success(
            Meal(
                "test", "test", listOf(
                    MeasuredIngredient("ingredient 1", "measurement 1"),
                    MeasuredIngredient("ingredient 2", "measurement 2")
                ),
                "test-thumbnail", "category", "area",
                "1\r\n2\r\n3"
            )
        )

        val scenario = launchFragmentInHiltContainer<InstructionsFragment>()

        onView(RecyclerViewMatcher(R.id.instructions).atPosition(0)).check(
            matches(
                ViewMatchers.withText(
                    "1"
                )
            )
        )
        onView(RecyclerViewMatcher(R.id.instructions).atPosition(1)).check(
            matches(
                ViewMatchers.withText(
                    "2"
                )
            )
        )
        onView(RecyclerViewMatcher(R.id.instructions).atPosition(2)).check(
            matches(
                ViewMatchers.withText(
                    "3"
                )
            )
        )


    }


}