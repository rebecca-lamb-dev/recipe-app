package lamb.rebecca.domain.usecase

import lamb.rebecca.domain.model.Meal
import lamb.rebecca.domain.model.Result
import lamb.rebecca.domain.repository.MealRepository
import javax.inject.Inject

class GetMealsByLetterUseCaseImpl @Inject constructor(private val mealRepository: MealRepository) :
    GetMealsByLetterUseCase {
    override suspend fun invoke(letter: String): Result<List<Meal>> {
        return mealRepository.getMealsByLetter(letter)
    }
}