package lamb.rebecca.recipeapp.presentation.main.ui

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.CoroutineScope

@Module
@InstallIn(ApplicationComponent::class)
class ViewModelModule {

    @Provides
    fun providesCustomCoroutineScope(): CoroutineScope? = null

}