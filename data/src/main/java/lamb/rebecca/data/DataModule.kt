package lamb.rebecca.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import lamb.rebecca.data.network.Api.Companion.retrofit
import lamb.rebecca.data.network.MealDbService.Companion.mealDbService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun providesRetrofit() = retrofit("https://www.themealdb.com/api/json/v1/1/")

    @Provides
    @Singleton
    fun providesApi(retrofit: Retrofit) = mealDbService(retrofit)

    @Provides
    @Singleton
    fun providesMealRepository(mealRepositoryImpl: lamb.rebecca.data.repository.MealRepositoryImpl): lamb.rebecca.domain.repository.MealRepository = mealRepositoryImpl

}