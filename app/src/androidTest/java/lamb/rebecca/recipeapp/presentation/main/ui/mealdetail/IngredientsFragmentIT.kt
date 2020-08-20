package lamb.rebecca.recipeapp.presentation.main.ui.mealdetail

import android.os.Bundle
import androidx.test.espresso.Espresso.onView
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import lamb.rebecca.recipeapp.R
import lamb.rebecca.recipeapp.RecyclerViewMatcher
import lamb.rebecca.recipeapp.hasDescendentWithIdAndText
import lamb.rebecca.recipeapp.launchFragmentInHiltContainer
import lamb.rebecca.recipeapp.presentation.main.ui.model.MealModel
import lamb.rebecca.recipeapp.presentation.main.ui.model.MeasuredIngredientModel
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class IngredientsFragmentIT {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Test
    fun canDisplayIngredientsInRecyclerView() {
        launchFragmentInHiltContainer(
            IngredientsFragment::class.java,
            R.id.mealDetailFragment,
            givenMealModel()
        )

        val firstItem = onView(RecyclerViewMatcher(R.id.ingredients).atPosition(0))
        firstItem.hasDescendentWithIdAndText(R.id.ingredient, "ingredient 1")
        firstItem.hasDescendentWithIdAndText(R.id.measurement, "measurement 1")

        val secondItem = onView(RecyclerViewMatcher(R.id.ingredients).atPosition(1))
        secondItem.hasDescendentWithIdAndText(R.id.ingredient, "ingredient 2")
        secondItem.hasDescendentWithIdAndText(R.id.measurement, "measurement 2")
    }

    @Test
    fun canDisplayIngredientsWithoutMeasurement() {
        launchFragmentInHiltContainer(
            IngredientsFragment::class.java,
            R.id.mealDetailFragment,
            givenMealModelWithoutMeasurement()
        )


        val firstItem = onView(RecyclerViewMatcher(R.id.ingredients).atPosition(0))
        firstItem.hasDescendentWithIdAndText(R.id.ingredient, "ingredient 1")
        firstItem.hasDescendentWithIdAndText(R.id.measurement, "")
    }

    private fun givenMealModel() = Bundle().apply {
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

    private fun givenMealModelWithoutMeasurement() = Bundle().apply {
        putParcelable(
            "meal_id", MealModel(
                "test", "test", listOf(
                    MeasuredIngredientModel("ingredient 1")
                ),
                "test-thumbnail", "category", "area",
                listOf("1", "2", "3")
            )
        )
    }

}