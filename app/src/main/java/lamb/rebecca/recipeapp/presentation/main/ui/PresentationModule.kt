package lamb.rebecca.recipeapp.presentation.main.ui

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import lamb.rebecca.domain.usecase.GetRandomMealUseCase
import lamb.rebecca.domain.usecase.GetRandomMealUseCaseImpl

@Module
@InstallIn(ActivityComponent::class)
abstract class PresentationModule {

    @Binds
    abstract fun bindsGetRandomMeal(getRandomMealImpl: GetRandomMealUseCaseImpl): GetRandomMealUseCase

}