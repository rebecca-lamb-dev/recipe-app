package lamb.rebecca.domain.usecase

import lamb.rebecca.domain.model.Meal
import lamb.rebecca.domain.model.Result

interface GetRandomMealUseCase {

    suspend operator fun invoke(): Result<Meal>

}