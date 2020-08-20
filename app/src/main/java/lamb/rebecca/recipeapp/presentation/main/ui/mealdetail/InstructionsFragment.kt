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
import lamb.rebecca.recipeapp.databinding.MealDetailInstructionsFragmentBinding

@AndroidEntryPoint
class InstructionsFragment : Fragment() {

    private val viewModel: MealDetailViewModel by navGraphViewModels(R.id.mealDetailFragment) {
        defaultViewModelProviderFactory
    }

    private lateinit var binding: MealDetailInstructionsFragmentBinding

    companion object {
        fun createInstructionsFragment(): InstructionsFragment {
            return InstructionsFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MealDetailInstructionsFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.meal.observe(viewLifecycleOwner, Observer { meal ->
            binding.instructions.adapter = MealDetailInstructionsAdapter(meal.instructions)
        })
    }

}