package lamb.rebecca.recipeapp.presentation.main.ui.mealdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import lamb.rebecca.recipeapp.databinding.MealDetailInstructionsFragmentBinding

@AndroidEntryPoint
class InstructionsFragment : Fragment() {

    private val viewModel: MealDetailViewModel by activityViewModels()

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
        val binding = MealDetailInstructionsFragmentBinding.inflate(inflater)

        viewModel.meal.observe(viewLifecycleOwner, Observer { meal ->
            binding.instructions.adapter = MealDetailInstructionsAdapter(meal.instructions)
        })

        return binding.root
    }

}