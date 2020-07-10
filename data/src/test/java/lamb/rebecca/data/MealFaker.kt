package lamb.rebecca.data

class MealFaker {

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