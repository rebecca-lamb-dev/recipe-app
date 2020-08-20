package lamb.rebecca.recipeapp.presentation.main.ui.mealdetail

import android.os.Bundle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import lamb.rebecca.recipeapp.R
import lamb.rebecca.recipeapp.RecyclerViewMatcher
import lamb.rebecca.recipeapp.launchFragmentInHiltContainer
import lamb.rebecca.recipeapp.presentation.main.ui.model.MealModel
import lamb.rebecca.recipeapp.presentation.main.ui.model.MeasuredIngredientModel
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class InstructionsFragmentIT {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Test
    fun canDisplayInstructions() {
        launchFragmentInHiltContainer(
            InstructionsFragment::class.java,
            R.id.mealDetailFragment,
            givenMealModelBundle()
        )

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

    private fun givenMealModelBundle() = Bundle().apply {
        putParcelable(
            "meal_id", MealModel(
                "test", "test", listOf(
                    MeasuredIngredientModel("ingredient 1", "measurement 1"),
                    MeasuredIngredientModel("ingredient 2", "measurement 2")
                ),
                "test-thumbnail", "category", "area",
                listOf("1", "2", "3")
            )
        )
    }

}