package lamb.rebecca.data.network.model

import lamb.rebecca.domain.model.MeasuredIngredient
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class MeasuredIngredientDataModelTest {

    @Test
    fun canConvertToDomainModel() {
        val measuredIngredientDataModel = MeasuredIngredientEntity("ingredient", "measurement")
        assertThat(measuredIngredientDataModel.toDomainModel()).isEqualTo(
            MeasuredIngredient(
                "ingredient",
                "measurement"
            )
        )
    }

}