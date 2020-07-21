package lamb.rebecca.recipeapp.presentation.main.ui.meallist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.meal_item.*
import lamb.rebecca.recipeapp.databinding.MealsListFragmentBinding
import lamb.rebecca.recipeapp.presentation.main.ui.helpers.ImageLoader
import javax.inject.Inject

@AndroidEntryPoint
class MealsFragment : Fragment() {

    companion object {

        @JvmStatic
        fun newInstance() = MealsFragment()

    }

    private val viewModel: MealsViewModel by viewModels()

    @Inject
    lateinit var imageLoader: ImageLoader

    private val mealsAdapter: MealsAdapter by lazy { MealsAdapter(imageLoader, ::onClick) }

    private fun onClick(id: String) {
        findNavController().navigate(MealsFragmentDirections.actionMealsFragmentToMealDetailFragment())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = MealsListFragmentBinding.inflate(inflater, container, false)

        with(binding.root) {
            layoutManager = GridLayoutManager(context, 2)
            adapter = mealsAdapter
        }

        viewModel.meals.observe(viewLifecycleOwner, Observer {
            mealsAdapter.setItems(it)
        })

        return binding.root
    }

}