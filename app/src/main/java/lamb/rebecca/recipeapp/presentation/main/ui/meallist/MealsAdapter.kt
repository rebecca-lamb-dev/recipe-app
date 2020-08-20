package lamb.rebecca.recipeapp.presentation.main.ui.meallist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import lamb.rebecca.recipeapp.R
import lamb.rebecca.recipeapp.presentation.main.ui.helpers.ImageLoader
import lamb.rebecca.recipeapp.presentation.main.ui.model.MealModel
import javax.inject.Inject

class MealsAdapter @Inject constructor(
    private val imageLoader: ImageLoader,
    private val clickListener: (meal: MealModel) -> Unit
) :
    RecyclerView.Adapter<MealsAdapter.ViewHolder>() {

    private var items: List<MealModel> = listOf()

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val image: ImageView = view.findViewById(R.id.image)
        private val label: TextView = view.findViewById(R.id.label)

        fun bind(item: MealModel) {
            imageLoader.loadImage(item.thumbnail, image)
            label.text = item.name
            view.setOnClickListener { clickListener(item) }
        }

    }

    fun setItems(items: List<MealModel>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.meal_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

}