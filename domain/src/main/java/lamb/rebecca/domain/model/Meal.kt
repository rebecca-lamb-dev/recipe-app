package lamb.rebecca.domain.model

data class Meal(
    val id: String,
    val name: String,
    val measuredIngredient: List<MeasuredIngredient> = listOf(),
    val thumbnail: String?,
    val category: String,
    val area: String) {
}