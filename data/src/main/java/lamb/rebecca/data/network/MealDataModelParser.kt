package lamb.rebecca.data.network

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.JsonReader
import lamb.rebecca.data.network.model.MealEntity
import lamb.rebecca.data.network.model.MeasuredIngredientEntity

class MealDataModelParser {
    companion object {
        private val NAME_OPTIONS = JsonReader.Options.of(
            "idMeal", "strMeal", "strCategory", "strDrinkAlternate",
            "strArea", "strInstructions", "strMealThumb",
            "strTags", "strYoutube", "strSource", "dateModified"
        )

        private enum class NAMES {
            ID, NAME, CATEGORY, DRINK, AREA, INSTRUCTIONS,
            THUMBNAIL, TAGS, YOUTUBE, SOURCE, DATE_MODIFIED
        }

        private val NON_NUMERIC_REGEX = Regex("[A-Za-z ]")
        private const val INGREDIENT_PREFIX = "strIngredient"
        private const val MEASUREMENT_PREFIX = "strMeasure"
    }

    private data class IndexedString(val index: Int, val value: String?)

    @FromJson
    fun parse(reader: JsonReader): MealEntity {
        var id: String? = null
        var meal: String? = null
        var thumbnail: String? = null
        val ingredientsMap = mutableMapOf<Int, String?>()
        val measurementsMap = mutableMapOf<Int, String?>()

        reader.beginObject()
        while (reader.hasNext()) {
            when (reader.selectName(NAME_OPTIONS)) {
                NAMES.ID.ordinal -> id = reader.nextString()
                NAMES.NAME.ordinal -> meal = reader.nextString()
                NAMES.THUMBNAIL.ordinal -> thumbnail = reader.nextString()
                -1 -> {
                    if (reader.peek() == JsonReader.Token.NAME) {
                        val name = reader.nextName()
                        // API does not return ingredients/measurements in list,
                        // but as individual numbered entries eg strIngredient1, strIngredient2
                        if (name.startsWith(INGREDIENT_PREFIX)) {
                            val ingredient = parseIndexedString(reader, name)
                            ingredientsMap[ingredient.index] = ingredient.value
                        } else if (name.startsWith(MEASUREMENT_PREFIX)) {
                            val measurement = parseIndexedString(reader, name)
                            measurementsMap[measurement.index] = measurement.value
                        }
                    }
                }
                else -> {
                    reader.skipValue()
                }
            }
        }
        reader.endObject()

        if (id == null) {
            throw JsonDataException("Null id parsing meal")
        }
        if (meal == null) {
            throw JsonDataException("Null meal parsing $id")
        }

        return MealEntity(
            id,
            meal,
            mapIngredientsToList(ingredientsMap, measurementsMap),
            thumbnail
        )
    }

    private fun parseIndexedString(reader: JsonReader, name: String): IndexedString {
        val index = name.replace(NON_NUMERIC_REGEX, "").toInt()
        val value =
            if (reader.peek() == JsonReader.Token.STRING) reader.nextString() else reader.nextNull()
        return IndexedString(index, value)
    }

    private fun hasValue(value: String?) = value != null && value.trim() != ""

    private fun mapIngredientsToList(
        ingredients: Map<Int, String?>,
        measurements: Map<Int, String?>
    ): List<MeasuredIngredientEntity> {
        if (ingredients.size != measurements.size) {
            throw JsonDataException("Ingredients and measurements do not match $ingredients, $measurements")
        }
        return ingredients.mapNotNull { entry ->
            val ingredient = entry.value
            val measurement = measurements[entry.key]

            if (hasValue(ingredient) && hasValue(measurement)) {
                MeasuredIngredientEntity(
                    ingredient!!,
                    measurement!!
                )
            } else if (hasValue(ingredient) != hasValue(measurement)) {
                throw JsonDataException("Ingredients and measurements do not match $ingredients, $measurements")
            } else {
                null
            }
        }
    }
}

