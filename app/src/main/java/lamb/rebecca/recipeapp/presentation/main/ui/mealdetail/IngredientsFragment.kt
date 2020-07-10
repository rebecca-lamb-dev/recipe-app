package lamb.rebecca.recipeapp.presentation.main.ui.mealdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import lamb.rebecca.recipeapp.databinding.MealDetailIngredientsFragmentBinding

@AndroidEntryPoint
class IngredientsFragment : Fragment() {

    private val viewModel: MealDetailViewModel by activityViewModels()

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
        val binding = MealDetailIngredientsFragmentBinding.inflate(inflater)

        viewModel.meal.observe(viewLifecycleOwner, Observer { meal ->
            binding.ingredients.adapter = MealDetailIngredientsAdapter(meal.ingredients)
        })

        return binding.root
    }

}