package lamb.rebecca.recipeapp.presentation.main.ui.mealdetail

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import io.mockk.coEvery
import io.mockk.mockk
import lamb.rebecca.domain.model.Meal
import lamb.rebecca.domain.model.Success
import lamb.rebecca.domain.usecase.GetRandomMealUseCase
import lamb.rebecca.recipeapp.R
import lamb.rebecca.recipeapp.launchFragmentInHiltContainer
import lamb.rebecca.recipeapp.presentation.main.ui.PresentationModule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@UninstallModules(PresentationModule::class)
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class MealDetailFragmentIT {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Module
    @InstallIn(ActivityComponent::class)
    class PresentationModule {

        @Provides
        fun providesGetRandomMeal(): GetRandomMealUseCase {
            val getRandomMealUseCase = mockk<GetRandomMealUseCase>()
            coEvery { getRandomMealUseCase() } returns Success(Meal("test", "test"))

            return getRandomMealUseCase
        }

    }

    @Test
    fun showFragment() {
        val scenario = launchFragmentInHiltContainer<MealDetailFragment>()

        onView(withId(R.id.message)).check(matches(withText("test test")))
    }
}