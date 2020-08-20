// Portions of this page are modifications based on work created and shared by the
// Android Open Source Project and used according to terms described in the
// Creative Commons 3.0 Attribution License.
// See: https://github.com/android/architecture-samples/blob/dev-hilt/app/src/androidTest/java/com/example/android/architecture/blueprints/todoapp/HiltExt.kt#L38

package lamb.rebecca.recipeapp

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import androidx.core.util.Preconditions
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelStore
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider

fun <T : Fragment> launchFragmentInHiltContainer(
    clazz: Class<T>,
    destinationId: Int? = null,
    fragmentArgs: Bundle? = null
) {
    val intent = createActivityIntent()
    ActivityScenario.launch<HiltTestActivity>(intent)
        .onActivity { activity ->
            val fragment = createFragment(activity, clazz, fragmentArgs)

            destinationId?.run {
                setupNavController(activity, destinationId)
            }

            activity.supportFragmentManager
                .beginTransaction()
                .add(android.R.id.content, fragment, "")
                .commitNow()
        }
}


private fun createActivityIntent() = Intent.makeMainActivity(
    ComponentName(
        ApplicationProvider.getApplicationContext(),
        HiltTestActivity::class.java
    )
)

private fun <T : Fragment> createFragment(
    activity: HiltTestActivity,
    fragmentClass: Class<T>,
    fragmentArgs: Bundle?
) =
    activity.supportFragmentManager.fragmentFactory.instantiate(
        Preconditions.checkNotNull(fragmentClass.classLoader),
        fragmentClass.name
    ).apply {
        this.arguments = fragmentArgs
    }

private fun setupNavController(
    activity: HiltTestActivity,
    destinationId: Int
) {
    val navController =
        TestNavHostController(ApplicationProvider.getApplicationContext())
    navController.setViewModelStore(ViewModelStore())
    navController.setGraph(R.navigation.navigation_graph)
    navController.setCurrentDestination(destinationId)
    activity.supportFragmentManager
        .registerFragmentLifecycleCallbacks(
            object : FragmentManager.FragmentLifecycleCallbacks() {
                override fun onFragmentCreated(
                    fragmentManager: FragmentManager,
                    fragment: Fragment,
                    savedInstanceState: Bundle?
                ) {
                    fragment.viewLifecycleOwnerLiveData.observeForever { viewLifecycleOwner ->
                        if (viewLifecycleOwner != null) {
                            Navigation.setViewNavController(fragment.requireView(), navController)
                        }
                    }
                }
            }, true
        )
}




