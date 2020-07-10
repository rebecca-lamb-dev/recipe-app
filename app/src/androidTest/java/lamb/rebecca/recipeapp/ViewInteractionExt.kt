package lamb.rebecca.recipeapp

import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Matchers

fun ViewInteraction.hasDescendentWithIdAndText(id: Int, text: String) {
    check(
        ViewAssertions.matches(
            ViewMatchers.hasDescendant(
                Matchers.allOf(
                    ViewMatchers.withId(id),
                    ViewMatchers.withText(text)
                )
            )
        )
    )
}