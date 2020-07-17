package lamb.rebecca.recipeapp.presentation.main.ui.model

import lamb.rebecca.domain.model.MeasuredIngredient
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class MeasuredIngredientModelTest {

    @Test
    fun convertFromDomain() {
        val measuredIngredient = MeasuredIngredient("i", "m")
        assertThat(MeasuredIngredientModel.fromDomain(measuredIngredient)).isEqualTo(
            MeasuredIngredientModel("i", "m")
        )
    }

    @Test
    fun convertFromDomainWithMissingMeasurement() {
        val measuredIngredient = MeasuredIngredient("i")
        assertThat(MeasuredIngredientModel.fromDomain(measuredIngredient)).isEqualTo(
            MeasuredIngredientModel("i")
        )
    }

}