package lamb.rebecca.domain.usecase

import lamb.rebecca.domain.model.Meal
import lamb.rebecca.domain.model.Result
import lamb.rebecca.domain.repository.MealRepository
import javax.inject.Inject

class GetRandomMealUseCaseImpl @Inject constructor(private val mealRepository: MealRepository):
    GetRandomMealUseCase {
    override suspend fun invoke(): Result<Meal> {
        return mealRepository.getRandomMeal()
    }
}