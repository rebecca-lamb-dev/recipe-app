package lamb.rebecca.data.network.model

import lamb.rebecca.domain.model.Meal

data class MealEntity(
    val id: String,
    val name: String,
    val ingredients: List<MeasuredIngredientEntity> = listOf()
) {

    fun toDomain() = Meal(
        id,
        name,
        ingredients.map { it.toDomainModel() })

}