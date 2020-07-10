package lamb.rebecca.data.network.model

import lamb.rebecca.domain.model.MeasuredIngredient

data class MeasuredIngredientEntity(val ingredient: String, val measurement: String) {
    fun toDomainModel() = MeasuredIngredient(ingredient, measurement)
}