package lamb.rebecca.recipeapp.presentation.main.ui.helpers

import android.content.Context
import android.widget.ImageView
import dagger.hilt.android.qualifiers.ActivityContext
import lamb.rebecca.recipeapp.presentation.main.ui.GlideApp
import lamb.rebecca.recipeapp.presentation.main.ui.GlideRequests
import javax.inject.Inject

interface ImageLoader {
    fun loadImage(url: String?, imageView: ImageView, placeholder: Int = -1)
}

class GlideImageLoader @Inject constructor(@ActivityContext context: Context) : ImageLoader {

    private val glide: GlideRequests = GlideApp.with(context)

    override fun loadImage(url: String?, imageView: ImageView, placeholder: Int) {
        if (url != null) {
            glide.load(url)
                .placeholder(placeholder)
                .into(imageView)
        } else {
            glide.load(placeholder).into(imageView)
        }
    }

}