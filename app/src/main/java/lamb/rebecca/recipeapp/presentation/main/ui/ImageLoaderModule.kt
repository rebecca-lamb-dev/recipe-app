package lamb.rebecca.recipeapp.presentation.main.ui

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import lamb.rebecca.recipeapp.presentation.main.ui.helpers.GlideImageLoader
import lamb.rebecca.recipeapp.presentation.main.ui.helpers.ImageLoader

@Module
@InstallIn(ActivityComponent::class)
abstract class ImageLoaderModule {

    @Binds
    abstract fun bindsImageLoader(imageLoader: GlideImageLoader): ImageLoader

}