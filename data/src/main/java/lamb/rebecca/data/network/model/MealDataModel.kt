package lamb.rebecca.data.network.model

import lamb.rebecca.domain.model.Meal

data class MealDataModel(
    val id: String,
    val name: String,
    val ingredients: List<MeasuredIngredientDataModel> = listOf()
) {

    fun toModel() = Meal(
        id,
        name,
        ingredients.map { it.toDomainModel() })

}