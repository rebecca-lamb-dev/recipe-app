package lamb.rebecca.recipeapp.presentation.main.ui.mealdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.hilt.lifecycle.HiltViewModelFactory
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import lamb.rebecca.recipeapp.R
import lamb.rebecca.recipeapp.databinding.MainFragmentBinding
import javax.inject.Inject

@AndroidEntryPoint
class MealDetailFragment : Fragment() {

    companion object {
        fun newInstance() =
            MealDetailFragment()
    }

    private val viewModel: MealDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = MainFragmentBinding.inflate(inflater)

        viewModel.meal.observe(viewLifecycleOwner, Observer { meal ->
            binding.message.text = "${meal.id} ${meal.name}"
        })

        return binding.root
    }

}