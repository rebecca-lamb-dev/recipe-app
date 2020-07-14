package lamb.rebecca.recipeapp.presentation.main.ui.mealdetail

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import lamb.rebecca.domain.model.Meal
import lamb.rebecca.domain.model.MeasuredIngredient
import lamb.rebecca.domain.model.Success
import lamb.rebecca.domain.usecase.GetRandomMealUseCase
import lamb.rebecca.recipeapp.R
import lamb.rebecca.recipeapp.RecyclerViewMatcher
import lamb.rebecca.recipeapp.hasDescendentWithIdAndText
import lamb.rebecca.recipeapp.launchFragmentInHiltContainer
import lamb.rebecca.recipeapp.presentation.main.ui.ImageLoaderModule
import lamb.rebecca.recipeapp.presentation.main.ui.PresentationModule
import lamb.rebecca.recipeapp.presentation.main.ui.helpers.ImageLoader
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@UninstallModules(PresentationModule::class, ImageLoaderModule::class)
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
        coEvery { getRandomMealUseCase() } returns Success(
            Meal(
                "test", "test", listOf(
                    MeasuredIngredient("1", "2"),
                    MeasuredIngredient("3", "4")
                ),
                "test-thumb", "category", "area"
            )
        )

        val scenario = launchFragmentInHiltContainer<MealDetailFragment>()

        onView(withId(R.id.title)).check(matches(withText("test")))
        onView(withId(R.id.category)).check(matches(withText("category")))
        onView(withId(R.id.area)).check(matches(withText("area")))

        onView(withId(R.id.main)).perform(ViewActions.swipeUp());

        val firstItem = onView(RecyclerViewMatcher(R.id.ingredients).atPosition(0))
        firstItem.hasDescendentWithIdAndText(R.id.ingredient, "1")
        firstItem.hasDescendentWithIdAndText(R.id.measurement, "2")

        val secondItem = onView(RecyclerViewMatcher(R.id.ingredients).atPosition(1))
        secondItem.hasDescendentWithIdAndText(R.id.ingredient, "3")
        secondItem.hasDescendentWithIdAndText(R.id.measurement, "4")

        verify { imageLoader.loadImage("test-thumb", any()) }
    }
}