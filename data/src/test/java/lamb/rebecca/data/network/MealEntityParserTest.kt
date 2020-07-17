package lamb.rebecca.data.network

import com.squareup.moshi.JsonDataException
import com.squareup.moshi.JsonReader
import lamb.rebecca.data.MealFaker
import lamb.rebecca.data.ResourceUtils
import lamb.rebecca.data.network.model.MeasuredIngredientEntity
import lamb.rebecca.domain.model.MeasuredIngredient
import okio.buffer
import okio.source
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.Test

class MealEntityParserTest {

    @Test
    fun parseMealDataModel() {
        val parser = MealEntityParser()
        val stream = ResourceUtils.readResourceAsStream("/meal.json")
        stream.source().buffer().use {
            val mealDataEntity = parser.parse(JsonReader.of(it))
            assertThat(mealDataEntity).isEqualTo(MealFaker().generateMealEntity())
        }
    }

    @Test
    fun errorsOnNullId() {
        val parser = MealEntityParser()
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
        val parser = MealEntityParser()
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
        val parser = MealEntityParser()
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
        val parser = MealEntityParser()
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
    fun acceptsIngredientWithoutMeasurement() {
        val parser = MealEntityParser()
        val stream = MealFaker().generateMealJson(measurement = "").byteInputStream()
        stream.source().buffer().use {
            val meal = parser.parse(JsonReader.of(it))
            assertThat(meal.ingredients).hasSize(1)
            assertThat(meal.ingredients[0]).isEqualTo(MeasuredIngredientEntity("plain flour"))
        }
    }

    @Test
    fun errorsOnIngredientWithNullIngredient() {
        val parser = MealEntityParser()
        val stream = MealFaker().generateMealJson(ingredient = null).byteInputStream()
        stream.source().buffer().use {
            assertThatThrownBy {
                parser.parse(JsonReader.of(it))
            }
                .isExactlyInstanceOf(JsonDataException::class.java)
                .hasMessage("Ingredients and measurements do not match {1=null}, {1=175g/6oz}")
        }
    }

}