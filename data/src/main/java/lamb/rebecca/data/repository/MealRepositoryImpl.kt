package lamb.rebecca.data.repository

import com.squareup.moshi.JsonDataException
import lamb.rebecca.data.network.MealDbService
import lamb.rebecca.data.network.model.Entity
import lamb.rebecca.domain.model.*
import lamb.rebecca.domain.repository.MealRepository
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MealRepositoryImpl @Inject constructor(private val mealDbService: MealDbService) :
    MealRepository {

    private inline fun <A : Any, R : Entity<A>> executeRequest(block: () -> R): Result<A> {
        return try {
            Success(block().toDomain())
        } catch (e: HttpException) {
            Failure(HttpError(e.code(), e.message()))
        } catch (e: UnknownHostException) {
            Failure(UnknownHostError(e.message))
        } catch (e: JsonDataException) {
            Failure(InvalidDataError)
        }
    }

    override suspend fun getRandomMeal(): Result<Meal> {
        return executeRequest { mealDbService.getRandomMeal() }
    }

    override suspend fun getMealsByLetter(letter: String): Result<List<Meal>> {
        return executeRequest { mealDbService.getMealsForLetter(letter) }
    }

}