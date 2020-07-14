package lamb.rebecca.recipeapp.presentation.main.ui.mealdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import lamb.rebecca.recipeapp.R
import lamb.rebecca.recipeapp.databinding.MealDetailFragmentBinding
import lamb.rebecca.recipeapp.presentation.main.ui.helpers.ImageLoader
import lamb.rebecca.recipeapp.presentation.main.ui.model.MealModel
import javax.inject.Inject

@AndroidEntryPoint
class MealDetailFragment : Fragment() {

    companion object {
        fun newInstance() =
            MealDetailFragment()
    }

    @Inject
    lateinit var imageLoader: ImageLoader

    private val viewModel: MealDetailViewModel by activityViewModels()

    private lateinit var mealDetailTabAdapter: MealDetailTabAdapter
    private lateinit var binding: MealDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MealDetailFragmentBinding.inflate(inflater)

        mealDetailTabAdapter = MealDetailTabAdapter(this)
        binding.viewpager.adapter = mealDetailTabAdapter

        viewModel.meal.observe(viewLifecycleOwner, Observer { meal ->
            displayMeal(meal)
        })

        TabLayoutMediator(binding.tabs, binding.viewpager) { tab, position ->
            tab.text = resources.getStringArray(R.array.meal_detail_tabs)[position]
        }.attach()

        return binding.root
    }

    private fun displayMeal(meal: MealModel) {
        (activity as AppCompatActivity).supportActionBar?.title = meal.name
        binding.category.text = meal.category
        binding.area.text = meal.area
        meal.thumbnail?.run {
            imageLoader.loadImage(this, binding.image)
        }
    }

    class MealDetailTabAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

        override fun getItemCount(): Int = 2

        override fun createFragment(position: Int): Fragment {
            if (position == 0) {
                return IngredientsFragment.createIngredientsFragment()
            } else {
                // Temp
                return IngredientsFragment.createIngredientsFragment()
            }
        }
    }

}