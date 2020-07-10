package lamb.rebecca.recipeapp.presentation.main.ui.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import lamb.rebecca.domain.model.MeasuredIngredient

@Parcelize
data class MeasuredIngredientModel(val ingredient: String, val measurement: String) : Parcelable {

    companion object {
        fun fromDomain(ingredient: MeasuredIngredient) =
            MeasuredIngredientModel(ingredient.ingredient, ingredient.measurement)
    }

}