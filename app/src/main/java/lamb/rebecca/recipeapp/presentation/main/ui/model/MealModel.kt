package lamb.rebecca.recipeapp.presentation.main.ui.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import lamb.rebecca.domain.model.Meal

@Parcelize
data class MealModel(
    val id: String,
    val name: String,
    val ingredients: List<MeasuredIngredientModel> = listOf(),
    val thumbnail: String?,
    val category: String,
    val area: String,
    val instructions: List<String>
) : Parcelable {

    companion object {
        fun fromDomain(meal: Meal) = MealModel(
            meal.id,
            meal.name,
            meal.measuredIngredient.map { MeasuredIngredientModel.fromDomain(it) },
            meal.thumbnail,
            meal.category,
            meal.area,
            generateFormattedInstructions(meal.instructions)
        )

        private fun generateFormattedInstructions(instructions: String): List<String> {
            return instructions.split("\r\n").filter { it != "" }
        }
    }
}
