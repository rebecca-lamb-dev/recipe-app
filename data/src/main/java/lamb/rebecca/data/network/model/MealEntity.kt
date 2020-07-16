package lamb.rebecca.data.network.model

import lamb.rebecca.domain.model.Meal

data class MealEntity(
    val id: String,
    val name: String,
    val ingredients: List<MeasuredIngredientEntity> = listOf(),
    val thumbnail: String?,
    val category: String,
    val area: String,
    val instructions: String
) {

    fun toDomain() = Meal(
        id,
        name,
        ingredients.map { it.toDomainModel() },
        thumbnail,
        category,
        area,
        instructions)

}