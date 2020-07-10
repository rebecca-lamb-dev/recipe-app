package lamb.rebecca.recipeapp.presentation.main.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import lamb.rebecca.recipeapp.R
import lamb.rebecca.recipeapp.presentation.main.ui.mealdetail.MealDetailFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container,
                        MealDetailFragment.newInstance()
                    )
                    .commitNow()
        }

    }
}