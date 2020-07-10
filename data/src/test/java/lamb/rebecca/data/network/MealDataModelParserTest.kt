package lamb.rebecca.data.network

import com.squareup.moshi.JsonDataException
import com.squareup.moshi.JsonReader
import lamb.rebecca.data.MealFaker
import lamb.rebecca.data.ResourceUtils
import lamb.rebecca.data.network.model.MealDataModel
import lamb.rebecca.data.network.model.MeasuredIngredientDataModel
import okio.buffer
import okio.source
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.Test

class MealDataModelParserTest {

    @Test
    fun parseMealDataModel() {
        val parser = MealDataModelParser()
        val stream = ResourceUtils.readResourceAsStream("/meal.json")
        stream.source().buffer().use {
            val mealDataModel = parser.parse(JsonReader.of(it))
            assertThat(mealDataModel).isEqualTo(expectedMealDataModel())
        }
    }

    @Test
    fun errorsOnNullId() {
        val parser = MealDataModelParser()
        val stream = MealFaker().generateMealJson(id = null).byteInputStream()
        stream.source().buffer().use {
            assertThatThrownBy {
                parser.parse(JsonReader.of(it))
            }
                .isExactlyInstanceOf(JsonDataException::class.java)
                .hasMessage("Expected a string but was NULL at path \$.idMeal")
        }
    }

    @Test
    fun errorsOnMissingId() {
        val parser = MealDataModelParser()
        val stream = MealFaker().generateMealJson(skipId = true).byteInputStream()
        stream.source().buffer().use {
            assertThatThrownBy {
                parser.parse(JsonReader.of(it))
            }
                .isExactlyInstanceOf(JsonDataException::class.java)
                .hasMessage("Null id parsing meal")
        }
    }

    @Test
    fun errorsOnNullName() {
        val parser = MealDataModelParser()
        val stream = MealFaker().generateMealJson(name = null).byteInputStream()
        stream.source().buffer().use {
            assertThatThrownBy {
                parser.parse(JsonReader.of(it))
            }
                .isExactlyInstanceOf(JsonDataException::class.java)
                .hasMessage("Expected a string but was NULL at path \$.strMeal")
        }
    }

    @Test
    fun errorsOnMissingName() {
        val parser = MealDataModelParser()
        val stream = MealFaker().generateMealJson(skipName = true).byteInputStream()
        stream.source().buffer().use {
            assertThatThrownBy {
                parser.parse(JsonReader.of(it))
            }
                .isExactlyInstanceOf(JsonDataException::class.java)
                .hasMessage("Null meal parsing 52767")
        }
    }

    @Test
    fun errorsOnIngredientWithMismatchedingredientsAndMeasurements() {
        val parser = MealDataModelParser()
        val stream = MealFaker().generateMealJson(skipMeasurement = true).byteInputStream()
        stream.source().buffer().use {
            assertThatThrownBy {
                parser.parse(JsonReader.of(it))
            }
                .isExactlyInstanceOf(JsonDataException::class.java)
                .hasMessage("Ingredients and measurements do not match {1=plain flour}, {}")
        }
    }

    @Test
    fun errorsOnIngredientWithNullIngredient() {
        val parser = MealDataModelParser()
        val stream = MealFaker().generateMealJson(ingredient = null).byteInputStream()
        stream.source().buffer().use {
            assertThatThrownBy {
                parser.parse(JsonReader.of(it))
            }
                .isExactlyInstanceOf(JsonDataException::class.java)
                .hasMessage("Ingredients and measurements do not match {1=null}, {1=175g/6oz}")
        }
    }

    @Test
    fun errorsOnIngredientWithNullMeasurement() {
        val parser = MealDataModelParser()
        val stream = MealFaker().generateMealJson(measurement = null).byteInputStream()
        stream.source().buffer().use {
            assertThatThrownBy {
                parser.parse(JsonReader.of(it))
            }
                .isExactlyInstanceOf(JsonDataException::class.java)
                .hasMessage("Ingredients and measurements do not match {1=plain flour}, {1=null}")
        }
    }

    private fun expectedMealDataModel() =
        MealDataModel(
            "52767", "Bakewell tart",
            listOf(
                MeasuredIngredientDataModel(
                    "plain flour",
                    "175g/6oz"
                ),
                MeasuredIngredientDataModel(
                    "chilled butter",
                    "75g/2½oz"
                )
            )
        )

}