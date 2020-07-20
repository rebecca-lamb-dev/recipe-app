package lamb.rebecca.domain.usecase

import lamb.rebecca.domain.model.Meal
import lamb.rebecca.domain.model.Result

interface GetMealsByLetterUseCase {

    suspend operator fun invoke(letter: String): Result<List<Meal>>

}