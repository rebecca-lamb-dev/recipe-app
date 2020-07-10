package lamb.rebecca.recipeapp.presentation.main.ui.mealdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import lamb.rebecca.recipeapp.databinding.IngredientViewBinding
import lamb.rebecca.recipeapp.presentation.main.ui.model.MeasuredIngredientModel

class MealDetailIngredientsAdapter(private val ingredients: List<MeasuredIngredientModel>) :
    RecyclerView.Adapter<MealDetailIngredientsAdapter.ViewHolder>() {

    class ViewHolder(binding: IngredientViewBinding) : RecyclerView.ViewHolder(binding.root) {

        private val ingredient: TextView = binding.ingredient
        private val measurement: TextView = binding.measurement

        fun bind(measuredIngredient: MeasuredIngredientModel) {
            ingredient.text = measuredIngredient.ingredient
            measurement.text = measuredIngredient.measurement
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            IngredientViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return ingredients.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(ingredients[position])
    }


}