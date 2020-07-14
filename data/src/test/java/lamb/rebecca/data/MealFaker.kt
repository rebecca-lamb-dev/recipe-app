package lamb.rebecca.data

import lamb.rebecca.data.network.model.MealEntity
import lamb.rebecca.data.network.model.MeasuredIngredientEntity
import lamb.rebecca.domain.model.Meal
import lamb.rebecca.domain.model.MeasuredIngredient

class MealFaker {

    fun generateMealEntity() = MealEntity(
        "52767", "Bakewell tart",
        listOf(
            MeasuredIngredientEntity(
                "plain flour",
                "175g/6oz"
            ),
            MeasuredIngredientEntity(
                "chilled butter",
                "75g/2½oz"
            )
        ),
        "https://www.themealdb.com/images/media/meals/wyrqqq1468233628.jpg",
        "Dessert",
        "British"
    )

    fun generateMeal() = Meal(
        "52767", "Bakewell tart",
        listOf(
            MeasuredIngredient(
                "plain flour",
                "175g/6oz"
            ),
            MeasuredIngredient(
                "chilled butter",
                "75g/2½oz"
            )
        ),
        "https://www.themealdb.com/images/media/meals/wyrqqq1468233628.jpg",
        "Dessert",
        "British"
    )

    fun generateMealJson(
        skipId: Boolean = false,
        skipName: Boolean = false,
        skipIngredient: Boolean = false,
        skipMeasurement: Boolean = false,
        id: String? = "52767",
        name: String? = "Bakewell tart",
        ingredient: String? = "plain flour",
        measurement: String? = "175g/6oz"
    ): String {

        val idLine = if (skipId) "" else {
            "\"idMeal\": ${if (id != null) "\"$id\"" else null},"
        }
        val nameLine = if (skipName) "" else {
            "\"strMeal\": ${if (name != null) "\"$name\"" else null},"
        }
        val ingredientLine = if (skipIngredient) "" else {
            "\"strIngredient1\": ${if (ingredient != null) "\"$ingredient\"" else null},"
        }
        val measurementLine = if (skipMeasurement) "" else {
            "\"strMeasure1\": ${if (measurement != null) "\"$measurement\"" else null},"
        }

        return """
                {
                  $idLine
                  $nameLine
                  "strDrinkAlternate": null,
                  "strCategory": "Dessert",
                  "strArea": "British",
                  "strInstructions": "To make the pastry",
                  "strMealThumb": "https://www.themealdb.com/images/media/meals/wyrqqq1468233628.jpg",
                  "strTags": "Tart,Baking,Alcoholic",
                  "strYoutube": "https://www.youtube.com/watch?v=1ahpSTf_Pvk",
                  $ingredientLine
                  $measurementLine
                  "strSource": null,
                  "dateModified": null
                }
            """
    }

}