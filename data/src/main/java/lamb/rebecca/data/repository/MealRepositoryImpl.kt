package lamb.rebecca.data.repository

import com.squareup.moshi.JsonDataException
import lamb.rebecca.data.network.MealDbService
import lamb.rebecca.domain.model.*
import lamb.rebecca.domain.repository.MealRepository
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MealRepositoryImpl @Inject constructor(private val mealDbService: MealDbService) :
    MealRepository {

    override suspend fun getRandomMeal(): Result<Meal> {
        try {
            val response = mealDbService.getRandomMeal()
            if (response.meals.isEmpty()) {
                return Failure(InvalidDataError)
            }
            return Success(response.meals[0].toDomain())
        } catch (e: HttpException) {
            return Failure(HttpError(e.code(), e.message()))
        } catch (e: UnknownHostException) {
            return Failure(UnknownHostError(e.message))
        } catch (e: JsonDataException) {
            return Failure(InvalidDataError)
        }
    }

}