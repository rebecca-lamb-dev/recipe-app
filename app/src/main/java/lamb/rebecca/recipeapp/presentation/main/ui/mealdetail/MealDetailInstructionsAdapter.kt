package lamb.rebecca.recipeapp.presentation.main.ui.mealdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import lamb.rebecca.recipeapp.databinding.InstructionItemBinding

class MealDetailInstructionsAdapter(private val items: List<String>) :
    RecyclerView.Adapter<MealDetailInstructionsAdapter.ViewHolder>() {

    class ViewHolder(private val binding: InstructionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(instruction: String) {
            binding.root.text = instruction
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            InstructionItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }


}