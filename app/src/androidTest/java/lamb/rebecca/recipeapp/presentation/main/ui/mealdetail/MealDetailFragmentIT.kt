package lamb.rebecca.recipeapp.presentation.main.ui.mealdetail

import android.os.Bundle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import io.mockk.mockk
import io.mockk.verify
import lamb.rebecca.domain.usecase.GetRandomMealUseCase
import lamb.rebecca.recipeapp.R
import lamb.rebecca.recipeapp.RecyclerViewMatcher
import lamb.rebecca.recipeapp.hasDescendentWithIdAndText
import lamb.rebecca.recipeapp.launchFragmentInHiltContainer
import lamb.rebecca.recipeapp.presentation.main.ui.ImageLoaderModule
import lamb.rebecca.recipeapp.presentation.main.ui.RandomMealModule
import lamb.rebecca.recipeapp.presentation.main.ui.helpers.ImageLoader
import lamb.rebecca.recipeapp.presentation.main.ui.model.MealModel
import lamb.rebecca.recipeapp.presentation.main.ui.model.MeasuredIngredientModel
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@UninstallModules(RandomMealModule::class, ImageLoaderModule::class)
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class MealDetailFragmentIT {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @BindValue
    @JvmField
    val getRandomMealUseCase: GetRandomMealUseCase = mockk()

    @BindValue
    @JvmField
    val imageLoader: ImageLoader = mockk(relaxed = true)

    @Test
    fun showFragment() {
        launchFragmentInHiltContainer(MealDetailFragment::class.java, R.id.mealDetailFragment, givenMealModel())

        onView(withId(R.id.toolbar)).check(matches(hasDescendant(withText("test"))))
        onView(withId(R.id.category)).check(matches(withText("category")))
        onView(withId(R.id.area)).check(matches(withText("area")))

        onView(withId(R.id.main)).perform(ViewActions.swipeUp())

        var firstItem = onView(RecyclerViewMatcher(R.id.instructions).atPosition(0))
        firstItem.check(matches(withText("1")))

        var secondItem = onView(RecyclerViewMatcher(R.id.instructions).atPosition(1))
        secondItem.check(matches(withText("2")))


        onView(withText(R.string.ingredients)).perform(ViewActions.click())
        firstItem = onView(RecyclerViewMatcher(R.id.ingredients).atPosition(0))
        firstItem.hasDescendentWithIdAndText(R.id.ingredient, "1")
        firstItem.hasDescendentWithIdAndText(R.id.measurement, "2")

        secondItem = onView(RecyclerViewMatcher(R.id.ingredients).atPosition(1))
        secondItem.hasDescendentWithIdAndText(R.id.ingredient, "3")
        secondItem.hasDescendentWithIdAndText(R.id.measurement, "4")

        verify { imageLoader.loadImage("test-thumb", any()) }
    }

    private fun givenMealModel() = Bundle().apply {
        putParcelable(
            "meal_id", MealModel(
                "test", "test", listOf(
                    MeasuredIngredientModel("1", "2"),
                    MeasuredIngredientModel("3", "4")
                ),
                "test-thumb", "category", "area",
                listOf("1", "2", "3")
            )
        )
    }

}