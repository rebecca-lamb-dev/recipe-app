package lamb.rebecca.recipeapp.presentation.main.ui.mealdetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import lamb.rebecca.recipeapp.R
import lamb.rebecca.recipeapp.databinding.MealDetailFragmentBinding

@AndroidEntryPoint
class MealDetailFragment : Fragment() {

    companion object {
        fun newInstance() =
            MealDetailFragment()
    }

    private val viewModel: MealDetailViewModel by activityViewModels()

    private lateinit var mealDetailTabAdapter: MealDetailTabAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = MealDetailFragmentBinding.inflate(inflater)

        mealDetailTabAdapter = MealDetailTabAdapter(this)
        binding.viewpager.adapter = mealDetailTabAdapter

        viewModel.meal.observe(viewLifecycleOwner, Observer { meal ->
            binding.title.text = meal.name
        })

        TabLayoutMediator(binding.tabs, binding.viewpager) { tab, position ->
            tab.text = resources.getStringArray(R.array.meal_detail_tabs)[position]
        }.attach()

        return binding.root
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