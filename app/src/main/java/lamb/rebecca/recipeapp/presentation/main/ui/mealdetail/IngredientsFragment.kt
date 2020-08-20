package lamb.rebecca.recipeapp.presentation.main.ui.mealdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.navGraphViewModels
import dagger.hilt.android.AndroidEntryPoint
import lamb.rebecca.recipeapp.R
import lamb.rebecca.recipeapp.databinding.MealDetailIngredientsFragmentBinding

@AndroidEntryPoint
class IngredientsFragment : Fragment() {

    private val viewModel: MealDetailViewModel by navGraphViewModels(R.id.mealDetailFragment) {
        defaultViewModelProviderFactory
    }

    private lateinit var binding: MealDetailIngredientsFragmentBinding

    companion object {
        fun createIngredientsFragment(): IngredientsFragment {
            return IngredientsFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MealDetailIngredientsFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.meal.observe(viewLifecycleOwner, Observer { meal ->
            binding.ingredients.adapter = MealDetailIngredientsAdapter(meal.ingredients)
        })
    }

}